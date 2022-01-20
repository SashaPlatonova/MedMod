package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.PatientDTO;
import ru.platonova.medmod.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patient/")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("find/fio")
    public PatientDTO findPatient(@RequestParam String name,
                                  @RequestParam String surName,
                                  @RequestParam String patronymic){
        return service.getPatient(name, surName, patronymic);
    }

    @GetMapping("find/id")
    public PatientDTO findPatientById(@RequestParam Long id){
        return service.getById(id);
    }

    @GetMapping("find/employee/id")
    public List<PatientDTO> findPatientByEmployeeId(@RequestParam Long id){
        return service.getPatientByEmployeeId(id);
    }

    @GetMapping("find/employee/name")
    public List<PatientDTO> findPatientByEmployee(@RequestParam String employee){
        return service.getPatientListByEmployee(employee);
    }

}
