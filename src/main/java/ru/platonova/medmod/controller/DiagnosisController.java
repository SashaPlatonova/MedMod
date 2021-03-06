package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.platonova.medmod.DTO.DiagnosisDTO;
import ru.platonova.medmod.service.DiagnosisService;

import java.util.List;

@RestController
@RequestMapping("api/diagnosis/")
public class DiagnosisController {
    @Autowired
    private DiagnosisService service;

    @GetMapping("all")
    public List<DiagnosisDTO> all(){
        return service.getAll();
    }

    @GetMapping("find/id")
    public DiagnosisDTO byId(@RequestParam Long id){
        return service.getById(id);
    }
    @GetMapping("find/code")
    public DiagnosisDTO byCode(@RequestParam String code){
        return service.getByCode(code);
    }
}
