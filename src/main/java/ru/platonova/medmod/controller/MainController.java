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

    @GetMapping("schedule/find/day")
    public List<ScheduleDTO> scheduleToDay(@RequestParam String date,
                                           @RequestParam Long id){
        return scheduleService.getForDay(date, id);
    }

    @GetMapping("schedule/find/employee")
    public List<ScheduleDTO> scheduleByEmployee(@RequestParam String employee){
        return scheduleService.getByEmployee(employee);
    }

    @GetMapping("schedule/find/employee/week")
    public List<ScheduleDTO> scheduleToWeek(@RequestParam String employee){
        return scheduleService.getForWeek(employee);
    }

    @GetMapping("schedule/find/last/id")
    public ScheduleDTO scheduleLast(@RequestParam Long id){
        return scheduleService.getLast(id);
    }

    @GetMapping("schedule/find/patient/id")
    public List<ScheduleDTO> scheduleByPatient(@RequestParam Long id){
        return scheduleService.getByPatient(id);
    }

    @GetMapping("patient/find/fio")
    public PatientDTO findPatient(@RequestParam String name,
                                  @RequestParam String surName,
                                  @RequestParam String patronymic){
        return patientService.getPatient(name, surName, patronymic);
    }

    @GetMapping("patient/find/id")
    public PatientDTO findPatientById(@RequestParam Long id){
        return patientService.getById(id);
    }

    @GetMapping("patient/find/employee/id")
    public List<PatientDTO> findPatientByEmployeeId(@RequestParam Long id){
        return patientService.getPatientByEmployeeId(id);
    }

    @GetMapping("patient/find/employee/name")
    public List<PatientDTO> findPatientByEmployee(@RequestParam String employee){
        return patientService.getPatientListByEmployee(employee);
    }

    @GetMapping("document/find/patient")
    public List<SessionDTO> findDocsByPatient(@RequestParam Long id){
        return sessionService.getByPatient(id);
    }

    @GetMapping("document/find/employee")
    public List<SessionDTO> findDocsByEmployee(@RequestParam String surName){
        return sessionService.getByEmployee(surName);
    }

    @GetMapping("document/find/category")
    public List<SessionDTO> findDocsByCategory(@RequestParam String category){
        return sessionService.getByCategory(category);
    }

}
