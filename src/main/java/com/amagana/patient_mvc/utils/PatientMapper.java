package com.amagana.patient_mvc.utils;

import com.amagana.patient_mvc.dto.PatientDtoRequest;
import com.amagana.patient_mvc.dto.PatientDtoResponse;
import com.amagana.patient_mvc.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    Patient patientDtoRequestToPatient(PatientDtoRequest patientDtoRequest);
    PatientDtoResponse patientToPatientDtoResponse(Patient patient);
}
