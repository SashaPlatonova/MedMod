package ru.platonova.medmod.DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private Long id;
    private Long personnelNum;
    private String name;
    private String surName;
    private String patronymic;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String username;
    private String password;
    private String experience;
    private DepartmentDTO department;
    private String qualification;
    private String education;
    private String photo;
//    private RoleDTO roleId;

    public static Employee toEntity(EmployeeDTO model){
        return new Employee(
                model.getId(),
                model.getName(),
                model.getSurName(),
                model.getPatronymic(),
                model.getGender(),
                model.getEmail(),
                model.getPhoneNumber(),
                model.getBirthDate(),
                model.getPersonnelNum(),
                model.getUsername(),
                model.getPassword(),
                model.getExperience(),
                DepartmentDTO.toEntity(model.getDepartment()),
                model.getQualification(),
                model.getPhoto(),
                model.getEducation()
        );
    }

    public static EmployeeDTO toModel(Employee employee){
        return new EmployeeDTO(employee.getId(), employee.getPersonnelNum(), employee.getName(), employee.getSurName(), employee.getPatronymic(),
                employee.getGender(), employee.getEmail(), employee.getPhoneNumber(), employee.getBirthDate(), employee.getUsername(),
                employee.getPassword(),
                employee.getExperience(),
                DepartmentDTO.toModel(employee.getDepartment()),
                employee.getQualification(), employee.getEducation(),
                employee.getPhoto());
    }


}
