package com.amagana.patient_mvc.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PatientDtoRequest(
        @Size(min = 4, message = "Name must have 4 characters min")
        String name,
        LocalDate bornDate,
        boolean isSick,
        @Min(value = 1, message = "Score must be greater than 0")
        int score) {
}
