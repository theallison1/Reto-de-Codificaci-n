package com.example.retoCodificacion.controller;


import com.example.retoCodificacion.domain.Candidate;
import com.example.retoCodificacion.exceptions.CandidateNotFoundException;
import com.example.retoCodificacion.service.CandidateService;
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

    // Crear un nuevo candidato
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@RequestBody @Valid Candidate candidate) {
        Candidate createdCandidate = candidateService.createCandidate(candidate);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
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
            candidateService.deleteCandidate(id); // Llama al servicio
            return ResponseEntity.noContent().build(); // Si la eliminación es exitosa
        } catch (CandidateNotFoundException e) {
            return ResponseEntity.notFound().build(); // Si el candidato no es encontrado
        }
    }

}