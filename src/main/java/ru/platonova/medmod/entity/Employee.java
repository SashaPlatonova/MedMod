package ru.platonova.medmod.entity;


import lombok.*;
import ru.platonova.medmod.DTO.EmployeeDTO;

import javax.persistence.*;


@SuppressWarnings("PMD")
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee extends Person{

    @Column(nullable = false)
    private Long personnelNum;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String qualification;

    @Column
    private String photo;

    @Column
    private String education;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private EmployeeRole roleId;


    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                '}';
    }
}
