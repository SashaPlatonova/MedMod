package ru.platonova.medmod.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employeeRole")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeRole {

    @Id
    @Column
    private Long roleId;

    @Column(nullable = false)
    private String roleName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId")
    private List<Employee> employees;

}
