package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Diagnosis;

public interface DiagnosisRepo extends CrudRepository<Diagnosis, Long> {

    Diagnosis findDiagnosesById(Long id);
    Diagnosis findDiagnosesByCode(String code);
}
