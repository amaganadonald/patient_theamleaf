package com.amagana.patient_mvc.service;

import com.amagana.patient_mvc.dto.PatientDtoRequest;
import com.amagana.patient_mvc.dto.PatientDtoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {

    List<PatientDtoResponse> getAllPatient();
    Page<PatientDtoResponse> getPatientPage(String keyword, int page, int size);
    PatientDtoResponse createPatient(PatientDtoRequest patientDtoRequest);
    PatientDtoResponse updatePatient(PatientDtoRequest patientDtoRequest, Long id);
    PatientDtoResponse getPatientById(Long id);
    void deletePatient(Long id);
}
