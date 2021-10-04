package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.*;


@SuppressWarnings("PMD")
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee extends Person{

    @Column(nullable = false)
    private Long personnelNum;

    @Column(nullable = false)
    private String username;

    @Column
    private String experience;

    @Column
    private String education;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private EmployeeRole roleId;
}
