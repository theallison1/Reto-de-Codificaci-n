package com.example.retoCodificacion.service;

import com.example.retoCodificacion.model.AuthRequestDto;

import java.util.Map;

public interface AuthService {
    Map<String, String> authRequest(AuthRequestDto authRequestDto);

}