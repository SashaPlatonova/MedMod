package ru.platonova.medmod.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@SuppressWarnings("PMD")
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surName;

    @Column
    private String patronymic;

    @Column(nullable = false)
    private String gender;

    @Column
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Date birthDate;

    public Person(String name, String surName, String gender, String phoneNumber, Date birthDate) {
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }
}
