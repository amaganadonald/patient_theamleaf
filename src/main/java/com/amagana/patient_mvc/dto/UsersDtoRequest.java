package com.amagana.patient_mvc.dto;

import com.amagana.patient_mvc.validation.CheckPassword;

public record UsersDtoRequest(String username,
                              String password,
                              boolean active, @CheckPassword String ConfirmPassword) {
}
