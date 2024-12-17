package com.example.retoCodificacion.controller;

import com.example.retoCodificacion.domain.Candidate;
import com.example.retoCodificacion.exceptions.CandidateNotFoundException;
import com.example.retoCodificacion.model.DtoCandidate;
import com.example.retoCodificacion.service.CandidateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidates")
@Validated
@SecurityRequirement(name = "Bearer")  // Asegúrate de que el nombre coincida con el definido en OpenAPI
@RequiredArgsConstructor
@Slf4j
public class CandidatesController {

    private final CandidateService candidateService;

    // Obtener todos los candidatos
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        log.info("Fetching all candidates");
        List<Candidate> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    // Obtener un candidato por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable Long id) {
        log.info("Fetching candidate with ID: {}", id);
        try {
            Candidate candidate = candidateService.getCandidateById(id)
                    .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + id));
            return ResponseEntity.ok(candidate);
        } catch (CandidateNotFoundException ex) {
            log.error("Candidate not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Candidate not found", "message", ex.getMessage()));
        }
    }

    // Crear un nuevo candidato
    @PostMapping
    public ResponseEntity<?> createCandidate(@RequestBody @Valid DtoCandidate dtoCandidate) {
        log.info("Creating a new candidate: {}", dtoCandidate);
        Candidate createdCandidate = candidateService.createCandidate(dtoCandidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
    }

    // Actualizar un candidato existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCandidate(@PathVariable Long id, @RequestBody @Valid Candidate candidate) {
        log.info("Updating candidate with ID: {}", id);
        try {
            Candidate updatedCandidate = candidateService.updateCandidate(id, candidate)
                    .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + id));
            return ResponseEntity.ok(updatedCandidate);
        } catch (CandidateNotFoundException ex) {
            log.error("Candidate not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Candidate not found", "message", ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id) {
        log.info("Deleting candidate with ID: {}", id);
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } catch (CandidateNotFoundException ex) {
            log.error("Candidate not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Candidate not found", "message", ex.getMessage()));
        } catch (Exception ex) {
            log.error("Unexpected error: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred", "message", ex.getMessage()));
        }
    }

}
