package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.entity.Department;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.service.EmployeeService;
import ru.platonova.medmod.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("employee/find")
    public List<EmployeeDTO> getEmployee(@RequestParam String surname){
        System.out.println(employeeService.getEmployeeBySurName(surname));
        return employeeService.getEmployeeBySurName(surname);
    }

    @GetMapping("employee/find/department")
    public List<EmployeeDTO> getEmployByDepartment(@RequestParam String department){
        return employeeService.getEmployeeByDepartment(department);
    }

    @GetMapping("schedule/find/day")
    public List<ScheduleDTO> scheduleToDay(@RequestParam String date){
        return scheduleService.getForDay(date);
    }

}
