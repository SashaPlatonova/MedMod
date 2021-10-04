package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
