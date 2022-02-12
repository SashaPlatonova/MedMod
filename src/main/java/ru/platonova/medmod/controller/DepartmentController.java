package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.platonova.medmod.DTO.DepartmentDTO;
import ru.platonova.medmod.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("find/all")
    public List<DepartmentDTO> getAll(){
        return service.getAll();
    }
}
