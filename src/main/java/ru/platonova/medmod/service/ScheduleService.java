package ru.platonova.medmod.service;

import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.entity.Schedule;
import ru.platonova.medmod.repository.ScheduleRepo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<ScheduleDTO> getForDay(String dateStr, Long id){
        Date date = Date.valueOf(dateStr);
        List<Schedule> schedules = scheduleRepo.findAllByDateAndEmployee(date, id);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        return scheduleDTOS;
    }

    public List<ScheduleDTO> getByEmployee(String surName){
        List<Schedule> schedules = scheduleRepo.findAllByEmployee(surName);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        return scheduleDTOS;
    }

    public List<ScheduleDTO> getForWeek(String surName){

        List<Schedule> schedules = scheduleRepo.findAllToWeek(surName);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        return scheduleDTOS;
    }

    public ScheduleDTO getLast(Long id){
        return ScheduleDTO.toModel(scheduleRepo.findLastByPatient(id));
    }

    public List<ScheduleDTO> getByPatient(Long id){
        List<Schedule> schedules = scheduleRepo.findByPatient(id);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        return scheduleDTOS;
    }
}
