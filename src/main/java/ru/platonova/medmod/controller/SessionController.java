package ru.platonova.medmod.controller;

import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/document/")
public class SessionController {

    @Autowired
    private SessionService service;

    @GetMapping("find/patient")
    public List<SessionDTO> findDocsByPatient(@RequestParam Long id){
        return service.getByPatient(id);
    }

    @GetMapping("find/employee")
    public List<SessionDTO> findDocsByEmployee(@RequestParam String surName){
        return service.getByEmployee(surName);
    }

    @GetMapping("find/category")
    public List<SessionDTO> findDocsByCategory(@RequestParam String category){
        return service.getByCategory(category);
    }

    @GetMapping("track")
    public JsonArray findAnalysisChanges(@RequestParam Long id, @RequestParam String name){
        return service.getAnalysisChanges(id, name);
    }
}
