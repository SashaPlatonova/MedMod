package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Department;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.entity.EmployeeRole;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {

    private Long personnelNum;
    private String name;
    private String surName;
    private String patronymic;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String username;
    private String experience;
    private DepartmentDTO department;
    private String qualification;
    private String education;
    private String photo;
    private RoleDTO roleId;

    public static EmployeeDTO toModel(Employee employee){
        return new EmployeeDTO(employee.getPersonnelNum(), employee.getName(), employee.getSurName(), employee.getPatronymic(),
                employee.getGender(), employee.getEmail(), employee.getPhoneNumber(), employee.getBirthDate(), employee.getUsername(),
                employee.getExperience(),
                DepartmentDTO.toModel(employee.getDepartment()),
                employee.getQualification(), employee.getEducation(),
                employee.getPhoto(), RoleDTO.toModel(employee.getRoleId()));
    }


}
