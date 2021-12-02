package ru.platonova.medmod.entity;


import lombok.*;
import ru.platonova.medmod.DTO.EmployeeDTO;

import javax.persistence.*;
import java.sql.Date;


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


    public Employee(Long id, String name, String surName, String patronymic, String gender,
                    String email, String phoneNumber, Date birthDate, Long personnelNum,
                    String username, String password, String experience, Department department,
                    String qualification, String photo, String education, EmployeeRole roleId) {
        super(id, name, surName, patronymic, gender, email, phoneNumber, birthDate);
        this.personnelNum = personnelNum;
        this.username = username;
        this.password = password;
        this.experience = experience;
        this.department = department;
        this.qualification = qualification;
        this.photo = photo;
        this.education = education;
        this.roleId = roleId;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                '}';
    }
}
