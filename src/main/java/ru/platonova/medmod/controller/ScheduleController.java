package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.platonova.medmod.DTO.Analysis;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("api/schedule/")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping("find/day")
    public List<ScheduleDTO> scheduleToDay(@RequestParam String date,
                                           @RequestParam Long id){
        return service.getForDay(date, id);
    }

    @GetMapping("find/employee")
    public List<ScheduleDTO> scheduleByEmployee(@RequestParam String employee){
        return service.getByEmployee(employee);
    }

    @GetMapping("find/employee/week")
    public List<ScheduleDTO> scheduleToWeek(@RequestParam String employee){
        return service.getForWeek(employee);
    }

    @GetMapping("find/last/id")
    public List<ScheduleDTO> scheduleLast(@RequestParam Long id){
        return service.getLast(id);
    }

    @GetMapping("find/patient/id")
    public List<ScheduleDTO> scheduleByPatient(@RequestParam Long id){
        return service.getByPatient(id);
    }

    @GetMapping("find/patient/indicator")
    public List<Analysis> analysesByPatientAndName(@RequestParam Long id, @RequestParam String name){
        return service.getAnalysisTable(id, name);
    }
}
