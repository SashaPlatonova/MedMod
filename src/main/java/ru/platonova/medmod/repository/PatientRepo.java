package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Patient;

public interface PatientRepo extends CrudRepository<Patient, Long> {
}
