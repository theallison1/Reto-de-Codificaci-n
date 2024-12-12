package com.example.retoCodificacion.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El correo electrónico es obligatorio")
    private String email;

    @NotNull(message = "El género es obligatorio")
    private String gender;

    @NotNull(message = "El salario esperado es obligatorio")
    private Double expectedSalary;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
