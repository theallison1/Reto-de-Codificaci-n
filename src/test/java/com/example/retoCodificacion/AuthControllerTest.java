package com.example.retoCodificacion;

import com.example.retoCodificacion.controller.AuthController;
import com.example.retoCodificacion.model.AuthRequestDto;
import com.example.retoCodificacion.security.SecurityConfig;
import com.example.retoCodificacion.service.AuthService;
import com.example.retoCodificacion.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;

    @Test
    void testAuthRequestSuccess() throws Exception {
        // Datos de entrada simulados
        AuthRequestDto authRequestDto = new AuthRequestDto("testuser", "testpassword");

        // Respuesta simulada del servicio (un token "dummy")
        Map<String, String> response = Map.of("token", "dummy-token");

        // Configuración del mock del servicio para que devuelva el token
        when(authService.authRequest(authRequestDto)).thenReturn(response);

        // Realizar la solicitud POST y validar la respuesta
        mockMvc.perform(post("/auth/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").value("dummy-token"));
    }
}
