package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.DTO.PatientDTO;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.entity.Department;
import ru.platonova.medmod.entity.Employee;
import ru.platonova.medmod.service.EmployeeService;
import ru.platonova.medmod.service.PatientService;
import ru.platonova.medmod.service.ScheduleService;
import ru.platonova.medmod.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MainController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("employee/find")
    public List<EmployeeDTO> getEmployee(@RequestParam String surname){
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

    @GetMapping("schedule/find/employee")
    public List<ScheduleDTO> scheduleByEmployee(@RequestParam String employee){
        return scheduleService.getByEmployee(employee);
    }

    @GetMapping("schedule/find/employee/week")
    public List<ScheduleDTO> scheduleToWeek(@RequestParam String employee){
        return scheduleService.getForWeek(employee);
    }

    @GetMapping("patient/find/fio")
    public PatientDTO findPatient(@RequestParam String name,
                                  @RequestParam String surName,
                                  @RequestParam String patronymic){
        return patientService.getPatient(name, surName, patronymic);
    }

    @GetMapping("patient/find")
    public List<PatientDTO> findPatientByEmployee(@RequestParam String employee){
        return patientService.getPatientListByEmployee(employee);
    }

    @GetMapping("document/find/patient")
    public List<SessionDTO> findDocsByPatient(@RequestParam String snils){
        return sessionService.getByPatient(snils);
    }

    @GetMapping("document/find/employee")
    public List<SessionDTO> findDocsByEmployee(@RequestParam String surName){
        return sessionService.getByEmployee(surName);
    }

    @GetMapping("documant/find/category")
    public List<SessionDTO> findDocsByCategory(@RequestParam String category){
        return sessionService.getByCategory(category);
    }

}
