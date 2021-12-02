package ru.platonova.medmod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.PatientDTO;
import ru.platonova.medmod.entity.Patient;
import ru.platonova.medmod.repository.PatientRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class PatientService {
    private final PatientRepo patientRepo;

    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public List<PatientDTO> getPatientByEmployeeId(Long id){
        List<Patient> patients = patientRepo.findAllByEmployeeId(id);
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOS.add(PatientDTO.toModel(patient));
        }
        return patientDTOS;
    }

    public PatientDTO getById(Long id){
        return PatientDTO.toModel(patientRepo.findPatientById(id));
    }

    public List<PatientDTO> getPatientListByEmployee(String employee){
        List<Patient> patients = patientRepo.findAllByEmployee(employee);
        List<PatientDTO> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOS.add(PatientDTO.toModel(patient));
        }
        return patientDTOS;
    }

    public PatientDTO getPatient(String name, String surName, String patronymic){
        return PatientDTO.toModel(patientRepo.findByNameAndSurNameAndPatronymic(name, surName, patronymic));
    }
}
