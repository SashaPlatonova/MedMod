package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends CrudRepository<Patient, Long> {

    @Query(value = "select distinct patient.id, patient.gender, patient.name, patient.sur_name, patient.patronymic, patient.birth_date, " +
            "patient.address, patient.email, patient.insurance_policy, patient.snils, patient.phone_number" +
            " from patient inner join session s on patient.id = s.patient_id" +
            " inner join schedule on s.id = schedule.session_id " +
            " where employee_id =:id", nativeQuery = true)
    List<Patient> findAllByEmployeeId(Long id);

    @Query(value = "select patient.id, patient.gender, patient.name, patient.sur_name, patient.patronymic, patient.birth_date, " +
            "patient.address, patient.email, patient.insurance_policy, patient.snils, patient.phone_number" +
            " from patient inner join session s on patient.id = s.patient_id" +
            " inner join schedule on s.id = schedule.session_id " +
            "inner join employee  on schedule.employee_id = employee.id" +
            " where employee.sur_name =:surName", nativeQuery = true)
    List<Patient> findAllByEmployee(String surName);

    Patient findByNameAndSurNameAndPatronymic(String name, String surName, String patronymic);

    Patient findPatientById(Long id);
}
