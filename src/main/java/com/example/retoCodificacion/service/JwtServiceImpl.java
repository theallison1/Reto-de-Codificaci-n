package com.example.retoCodificacion.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtServiceImpl implements JwtService {
    private final String secretKey;
    private final String refreshSecretKey = "refreshSecret"; // Otro secreto para los refresh tokens

    public JwtServiceImpl(@Value("${secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    // Generar un Access Token
    @Override
    public String generateAccessToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .addClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) // Expira en 5 minutos
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Generar un Refresh Token
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Expira en 7 días
                .signWith(getRefreshSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar el Refresh Token
    public boolean isRefreshTokenValid(String refreshToken, String username) {
        final String tokenUsername = extractUsername(refreshToken);
        return (tokenUsername.equals(username)) && !isTokenExpired(refreshToken);
    }

    // Extraer el nombre de usuario del token
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Verificar si el Access Token es válido
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Validar si el token está expirado
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraer la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extraer todas las reclamaciones del token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extraer todas las reclamaciones del token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Obtener la clave de firma para el Access Token
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    // Obtener la clave de firma para el Refresh Token
    private Key getRefreshSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshSecretKey));
    }
}
