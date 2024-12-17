package com.example.retoCodificacion.model;

import jakarta.validation.constraints.NotNull;

public class DtoCandidate {
    @NotNull(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El correo electrónico es obligatorio")
    private String email;

    @NotNull(message = "El género es obligatorio")
    private String gender;

    @NotNull(message = "El salario esperado es obligatorio")
    private Double expectedSalary;

    // Getters and Setters

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
}
