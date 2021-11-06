package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Patient;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {

    private String name;
    private String surName;
    private String patronymic;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String snils;
    private String address;
    private String insurancePolicy;

    public static PatientDTO toModel(Patient patient){
        return new PatientDTO(
                patient.getName(),
                patient.getSurName(),
                patient.getPatronymic(),
                patient.getGender(),
                patient.getEmail(),
                patient.getPhoneNumber(),
                patient.getBirthDate(),
                patient.getSnils(),
                patient.getAddress(),
                patient.getInsurancePolicy()
        );
    }
}
