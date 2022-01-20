package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employee/find")
    public List<EmployeeDTO> getEmployee(@RequestParam String surname){
        return employeeService.getEmployeeBySurName(surname);
    }

    @GetMapping("employee/find/id")
    public EmployeeDTO getEmployeeByID(@RequestParam Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("employee/find/department")
    public List<EmployeeDTO> getEmployByDepartment(@RequestParam String department){
        return employeeService.getEmployeeByDepartment(department);
    }

    @PutMapping("employee/update")
    public boolean updateEmployee(@RequestBody EmployeeDTO model){
        return employeeService.updateEmployee(model);
    }

}
