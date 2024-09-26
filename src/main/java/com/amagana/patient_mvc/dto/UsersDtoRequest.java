package com.amagana.patient_mvc.dto;

public record UsersDtoRequest(String username,
        String password,
        boolean active, String ConfirmPassword) {
}
