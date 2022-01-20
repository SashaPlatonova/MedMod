package ru.platonova.medmod.service;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.Analysis;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.entity.Patient;
import ru.platonova.medmod.entity.Schedule;
import ru.platonova.medmod.entity.Session;
import ru.platonova.medmod.repository.ScheduleRepo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<ScheduleDTO> getLast(Long id){
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Schedule> schedules = scheduleRepo.findLastByEmployee(id);
        Set<Patient> patientSet = new HashSet<>();
        for (Schedule schedule : schedules) {
            if(!patientSet.contains(schedule.getSession().getPatient())){
                patientSet.add(schedule.getSession().getPatient());
                scheduleDTOS.add(ScheduleDTO.toModel(schedule));
            }
        }

        return scheduleDTOS;
    }

    public List<ScheduleDTO> getByPatient(Long id){
        List<Schedule> schedules = scheduleRepo.findByPatient(id);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
        }
        for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            SessionDTO model = scheduleDTO.getSession();
            for (int i = 0; i<model.getConclusion().size(); i++){
                JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                float indicatorValue = -1;
                if(conclusion.has("Название")){
                    indicatorValue = conclusion.get("Значение").getAsFloat();
                    if(indicatorValue<conclusion.get("Минимально допустимое значение").getAsFloat() ||
                            indicatorValue>conclusion.get("Максимальное допустимое значение").getAsFloat()) {
                        conclusion.addProperty("Норма", false);
                    }
                }
            }
        }
        return scheduleDTOS;
    }

    public List<Analysis> getAnalysisTable(Long id, String name){
        List<Analysis> analysis = new ArrayList<>();
        List<ScheduleDTO> schedules = this.getByPatient(id);
        for (ScheduleDTO schedule : schedules) {
            SessionDTO model = schedule.getSession();
            for (int i = 0; i<model.getConclusion().size(); i++){
                JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                if(conclusion.has("Название")) {
                    if (conclusion.get("Название").getAsString().equals(name)) {
                        analysis.add(new Analysis(
                                conclusion.get("Название").getAsString(),
                                conclusion.get("Значение").getAsFloat(),
                                conclusion.get("Минимально допустимое значение").getAsFloat(),
                                conclusion.get("Максимальное допустимое значение").getAsFloat(),
                                conclusion.get("Минимально допустимое значение (Жен)").getAsFloat(),
                                conclusion.get("Максимальное допустимое значение (Жен)").getAsFloat(),
                                schedule.getDate()
                        ));
                    }
                }
            }
        }
        return analysis;
    }
}
