package com.example.retoCodificacion.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        // Lógica para verificar si el token es válido
        return true;  // Ejemplo, implementa la lógica adecuada
    }

    @Override
    public String extractUsername(String token) {
        // Lógica para extraer el nombre de usuario del token
        return "username";  // Ejemplo, implementa la lógica adecuada
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, String username) {
        // Lógica para generar el token JWT
        return "generated_token";  // Ejemplo, implementa la lógica adecuada
    }
}
