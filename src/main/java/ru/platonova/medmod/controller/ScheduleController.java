package ru.platonova.medmod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.platonova.medmod.DTO.Analysis;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("api/schedule/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping("find/day")
    public List<ScheduleDTO> scheduleToDay(@RequestParam String date,
                                           @RequestParam Long id){
        return service.getForDay(date, id);
    }

    @GetMapping("find/id")
    public ScheduleDTO findById(@RequestParam Long id){
        return service.findById(id);
    }

    @GetMapping("find/employee")
    public List<ScheduleDTO> scheduleByEmployee(@RequestParam String employee){
        return service.getByEmployee(employee);
    }
    @GetMapping("find/employee/id")
    public List<ScheduleDTO> scheduleByEmployee(@RequestParam Long id){
        return service.getByEmployeeId(id);
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

    @GetMapping("find/all/ordered")
    public List<ScheduleDTO> schedulePatientsOrderedList(){
        return service.findAllDateOrder();
    }

    @PutMapping("update")
    public void update(@RequestBody ScheduleDTO dto){
        service.update(dto);
    }

    @GetMapping("add/session")
    public ScheduleDTO addSession(@RequestParam Long id, @RequestParam Long session){
      return service.addSessionToSchedule(id, session);
    }
}
