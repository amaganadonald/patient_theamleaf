package com.amagana.patient_mvc.dto;

import java.time.LocalDate;

public record PatientDtoResponse(Long id, String name, LocalDate bornDate,boolean isSick,int score) {
}
