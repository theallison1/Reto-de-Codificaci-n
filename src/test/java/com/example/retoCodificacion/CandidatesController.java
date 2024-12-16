package com.example.retoCodificacion;

import com.example.retoCodificacion.domain.Candidate;
import com.example.retoCodificacion.exceptions.CandidateNotFoundException;
import com.example.retoCodificacion.service.CandidateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Nested
class CandidatesControllerTest {

    @InjectMocks
    private com.example.retoCodificacion.controller.CandidatesController candidatesController;

    @Mock
    private CandidateService candidateService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidatesController).build();
    }





    @Test
    public void testGetCandidateByIdNotFound() throws Exception {
        // Mock the behavior of the service
        when(candidateService.getCandidateById(anyLong())).thenReturn(Optional.empty());

        // Perform the GET request
        mockMvc.perform(get("/candidates/999"))
                .andExpect(status().isNotFound()); // Expecting HTTP 404 NOT FOUND
    }







    @Test
    public void testUpdateCandidateNotFound() throws Exception {
        when(candidateService.updateCandidate(anyLong(), any(Candidate.class))).thenReturn(Optional.empty());

        // Perform the PUT request
        mockMvc.perform(put("/candidates/999")
                        .contentType("application/json")
                        .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isNotFound()); // Expecting HTTP 404 NOT FOUND
    }

    @Test
    public void testDeleteCandidate() throws Exception {
        // Mocking the service behavior
        doNothing().when(candidateService).deleteCandidate(1L);

        // Perform the DELETE request and check the result
        mockMvc.perform(delete("/candidates/1"))
                .andExpect(status().isNoContent()); // Expecting HTTP 204 NO CONTENT
    }

    public String getAuthToken() throws Exception {
        // Usando las credenciales correctas: "admin" como username y "admin" como password
        String authRequestJson = "{\"username\":\"admin\", \"password\":\"admin\"}";

        // Simula la solicitud de login y obtiene el token
        String response = mockMvc.perform(post("/auth/v1/login")
                        .contentType("application/json")
                        .content(authRequestJson))
                .andExpect(status().isOk()) // Esperamos que la respuesta sea 200 OK
                .andReturn().getResponse().getContentAsString(); // Obtenemos la respuesta en formato String

        // Asumimos que la respuesta contiene el token en formato JSON
        return new ObjectMapper().readTree(response).get("token").asText(); // Extraemos el token
    }




}
