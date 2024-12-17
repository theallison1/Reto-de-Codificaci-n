package com.example.retoCodificacion.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    // Verificar si el token es válido
    boolean isTokenValid(String token, UserDetails userDetails);

    // Extraer el nombre de usuario del token
    String extractUsername(String token);

    // Generar un access token
    String generateAccessToken(Map<String, Object> extraClaims, String username);

    // Generar un refresh token
    String generateRefreshToken(String username);
}
