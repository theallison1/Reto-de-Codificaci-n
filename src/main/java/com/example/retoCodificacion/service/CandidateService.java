package com.example.retoCodificacion.service;


import com.example.retoCodificacion.domain.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    Candidate createCandidate(Candidate candidate);

    List<Candidate> getAllCandidates();

    Optional<Candidate> getCandidateById(Long id);
    Optional<Candidate> updateCandidate(Long id, Candidate candidate);  // Método de actualización

    void deleteCandidate(Long id);
}