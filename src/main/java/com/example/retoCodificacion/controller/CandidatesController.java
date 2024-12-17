package com.example.retoCodificacion.controller;

import com.example.retoCodificacion.domain.Candidate;
import com.example.retoCodificacion.exceptions.CandidateNotFoundException;
import com.example.retoCodificacion.model.DtoCandidate;
import com.example.retoCodificacion.service.CandidateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidates")
@Validated
@SecurityRequirement(name = "Bearer")  // Asegúrate de que el nombre coincida con el definido en OpenAPI
public class CandidatesController {

    private final CandidateService candidateService;

    @Autowired
    public CandidatesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Obtener todos los candidatos
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    // Obtener un candidato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody DtoCandidate dtoCandidate) {
        Candidate createdCandidate = candidateService.createCandidate(dtoCandidate);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody @Valid Candidate candidate) {
        Optional<Candidate> updatedCandidate = candidateService.updateCandidate(id, candidate);
        return updatedCandidate.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (CandidateNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 Not Found
        }
    }

}
