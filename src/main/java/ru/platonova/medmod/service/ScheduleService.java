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
    private ScheduleRepo scheduleRepo;

    public ScheduleService(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    public List<ScheduleDTO> getForDay(String dateStr){
        Date date = Date.valueOf(dateStr);
        List<Schedule> schedules = scheduleRepo.findAllByDate(date);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        return scheduleDTOS;
    }
}
