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

    public Candidate(long l, String janeDoe) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "El nombre es obligatorio") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "El nombre es obligatorio") String name) {
        this.name = name;
    }

    public @NotNull(message = "El correo electrónico es obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "El correo electrónico es obligatorio") String email) {
        this.email = email;
    }

    public @NotNull(message = "El género es obligatorio") String getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "El género es obligatorio") String gender) {
        this.gender = gender;
    }

    public @NotNull(message = "El salario esperado es obligatorio") Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(@NotNull(message = "El salario esperado es obligatorio") Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
