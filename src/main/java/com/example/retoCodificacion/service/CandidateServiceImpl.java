package com.example.retoCodificacion.service;


import com.example.retoCodificacion.domain.Candidate;
import com.example.retoCodificacion.exceptions.CandidateNotFoundException;
import com.example.retoCodificacion.repository.CandidateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }



    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }
    @Override
    public Optional<Candidate> updateCandidate(Long id, Candidate candidate) {
        // Buscar el candidato por ID y lanzar una excepción si no existe
        return candidateRepository.findById(id)
                .map(existingCandidate -> {
                    // Copiar las propiedades del candidato proporcionado al candidato encontrado
                    BeanUtils.copyProperties(candidate, existingCandidate, "id");

                    // Guardar el candidato actualizado
                    return candidateRepository.save(existingCandidate);
                });
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id " + id));

        candidateRepository.delete(candidate);
    }

}
