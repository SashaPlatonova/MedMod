package ru.platonova.medmod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.entity.Department;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.repository.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public EmployeeDTO getEmployeeById(Long id){
        return EmployeeDTO.toModel(employeeRepo.findEmployeeById(id));
    }

    public List<EmployeeDTO> getEmployeeBySurName(String surName){
        List<Employee> employees = employeeRepo.findAllBySurName(surName);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOS.add(EmployeeDTO.toModel(employee));
        }

        return employeeDTOS;
    }

    public List<EmployeeDTO> getEmployeeByDepartment(String department){
        List<Employee> employees = employeeRepo.findAllByDepartment(department);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOS.add(EmployeeDTO.toModel(employee));
        }

        return employeeDTOS;
    }

    public List<EmployeeDTO> all(){
        List<Employee> employees = (List<Employee>) employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOS.add(EmployeeDTO.toModel(employee));
        }

        return employeeDTOS;
    }

    public boolean updateEmployee(EmployeeDTO model){
        Employee emp = employeeRepo.findEmployeeById(model.getId());
        if(emp==null){
            return false;
        }
        System.out.println(emp.getBirthDate());
        emp = EmployeeDTO.toEntity(model);
        employeeRepo.save(emp);
        return true;
    }
}
