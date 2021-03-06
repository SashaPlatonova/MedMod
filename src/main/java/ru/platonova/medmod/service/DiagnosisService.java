package ru.platonova.medmod.service;

import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.DiagnosisDTO;
import ru.platonova.medmod.entity.Diagnosis;
import ru.platonova.medmod.repository.DiagnosisRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosisService {
    private final DiagnosisRepo diagnosisRepo;

    public DiagnosisService(DiagnosisRepo diagnosisRepo) {
        this.diagnosisRepo = diagnosisRepo;
    }

    public List<DiagnosisDTO> getAll(){
        List<DiagnosisDTO> diagnosisDTOS = new ArrayList<>();
        List<Diagnosis> diagnoses = (List<Diagnosis>) diagnosisRepo.findAll();
        for (Diagnosis d : diagnoses) {
            diagnosisDTOS.add(DiagnosisDTO.toModel(d));
        }
        return diagnosisDTOS;
    }

    public DiagnosisDTO getById(Long id) {
        return DiagnosisDTO.toModel(diagnosisRepo.findDiagnosesById(id));
    }

    public DiagnosisDTO getByCode(String code) {
        return DiagnosisDTO.toModel(diagnosisRepo.findDiagnosesByCode(code));
    }
}
