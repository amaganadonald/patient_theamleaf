package com.amagana.patient_mvc.service.impl;

import com.amagana.patient_mvc.Exception.EntityNotFoundException;
import com.amagana.patient_mvc.dto.PatientDtoRequest;
import com.amagana.patient_mvc.dto.PatientDtoResponse;
import com.amagana.patient_mvc.entities.Patient;
import com.amagana.patient_mvc.repository.PatientRepository;
import com.amagana.patient_mvc.service.PatientService;
import com.amagana.patient_mvc.utils.PatientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private static final PatientMapper patientMapper = PatientMapper.INSTANCE;

    @Override
    public List<PatientDtoResponse> getAllPatient() {
        return patientRepository.findAll().stream()
                .map(patientMapper::patientToPatientDtoResponse)
                .toList();
    }

    @Override
    public Page<PatientDtoResponse> getPatientPage(String keyword, int page, int size) {
        return patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(String.format("Patient with id %s not found", id)));
    }

    @Override
    public PatientDtoResponse createPatient(PatientDtoRequest patientDtoRequest) {
        return patientMapper.patientToPatientDtoResponse(
                patientRepository.save(patientMapper.patientDtoRequestToPatient(patientDtoRequest))
        );
    }

    @Override
    public PatientDtoResponse updatePatient(PatientDtoRequest patientDtoRequest, Long id) {
        Patient patient = findPatientById(id);
        patient.setBornDate(patientDtoRequest.bornDate());
        patient.setName(patientDtoRequest.name());
        patient.setScore(patientDtoRequest.score());
        patient.setSick(patientDtoRequest.isSick());
        return patientMapper.patientToPatientDtoResponse(
                patientRepository.save(patient)
        );
    }

    @Override
    public PatientDtoResponse getPatientById(Long id) {
        return patientMapper.patientToPatientDtoResponse(findPatientById(id));
    }

    @Override
    public void deletePatient(Long id) {
       Patient patient = findPatientById(id);
       patientRepository.deleteById(patient.getId());
    }
}
