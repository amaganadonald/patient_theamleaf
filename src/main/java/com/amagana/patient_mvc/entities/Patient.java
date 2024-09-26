package com.amagana.patient_mvc.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 4, message = "Name must have 4 characters min")
    private String name;
    private LocalDate bornDate;
    private boolean isSick;
    @Min(value = 1, message = "Score must be greater than 0")
    private int score;
}
