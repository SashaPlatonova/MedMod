package ru.platonova.medmod.controller;

import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.SessionCategoryDTO;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.service.ScheduleService;
import ru.platonova.medmod.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/document/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SessionController {

    @Autowired
    private SessionService service;

    @GetMapping("find/id")
    public SessionDTO findById(@RequestParam Long id){
        return service.findById(id);
    }

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

    @GetMapping("all/categories")
    public List<SessionCategoryDTO> getAll(){
        return service.getAllCatNames();
    }

    @DeleteMapping("delete")
    public void deleteSession(@RequestParam Long id){
        service.deleteSession(id);
    }

    @PostMapping("add")
    public Long addSession(@RequestBody SessionDTO session){
        return service.addSessionId(session);
    }

    @PutMapping("update")
    public void updateSession(@RequestBody SessionDTO dto){
        service.updateSession(dto);
    }
}
