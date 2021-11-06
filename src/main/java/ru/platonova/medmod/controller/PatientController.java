package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.PatientDTO;
import ru.platonova.medmod.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients/")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("find")
    public List<PatientDTO> findByEmployee(@RequestParam String employee){
        return service.getPatientListByEmployee(employee);
    }
}
