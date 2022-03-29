package ru.platonova.medmod.entity;


import lombok.*;
import ru.platonova.medmod.DTO.EmployeeDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


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

    @Column
    private String qualification;

    @Column
    private String photo;

    @Column
    private String education;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Employee(Long id, String name, String surName, String patronymic, String gender,
                    String email, String phoneNumber, Date birthDate, Long personnelNum,
                    String username, String password, String experience, Department department,
                    String qualification, String photo, String education) {
        super(id, name, surName, patronymic, gender, email, phoneNumber, birthDate);
        this.personnelNum = personnelNum;
        this.username = username;
        this.password = password;
        this.experience = experience;
        this.department = department;
        this.qualification = qualification;
        this.photo = photo;
        this.education = education;
    }

    public Employee(String username, String password, Long personnel,
                    String name, String surName, String phone, String gender, Date birthDate){
        super(name, surName, gender, phone, birthDate);
        this.username = username;
        this.password = password;
        this.personnelNum = personnel;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                '}';
    }
}
