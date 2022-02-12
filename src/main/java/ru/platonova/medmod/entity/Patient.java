package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@SuppressWarnings("PMD")
@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient extends Person{

    @Column(nullable = false)
    private String snils;

    @Column(nullable = false)
    private String address;

    @Column
    private String insurancePolicy;

    public Patient(Long id, String name, String surName, String patronymic,
                   String gender, String email, String phoneNumber, Date birthDate,
                   String snils, String address, String insurancePolicy) {
        super(id, name, surName, patronymic, gender, email, phoneNumber, birthDate);
        this.snils = snils;
        this.address = address;
        this.insurancePolicy = insurancePolicy;
    }
}
