package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Employee;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

     List<Employee> findAllBySurName(String surName);
     Employee findEmployeeById(Long id);

     @Query("select emp " +
             "from Employee emp " +
             "join fetch Department dep" +
             " on(dep.id=emp.department.id) where dep.name = :name")
     List<Employee> findAllByDepartment(String name);

}
