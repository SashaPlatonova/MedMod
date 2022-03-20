package ru.platonova.medmod.service;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.Analysis;
import ru.platonova.medmod.DTO.EmployeeDTO;
import ru.platonova.medmod.DTO.ScheduleDTO;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.entity.Patient;
import ru.platonova.medmod.entity.Schedule;
import ru.platonova.medmod.entity.Session;
import ru.platonova.medmod.repository.ScheduleRepo;
import ru.platonova.medmod.repository.SessionRepo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private SessionRepo sessionRepo;

    public ScheduleService(ScheduleRepo scheduleRepo, SessionRepo sessionRepo) {
        this.scheduleRepo = scheduleRepo;
        this.sessionRepo = sessionRepo;
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

    public List<ScheduleDTO> getByEmployeeId(Long id){
        List<Schedule> schedules = scheduleRepo.findAllByEmployeeID(id);
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
        for (Schedule schedule : schedules) {
//            if(!patientSet.contains(schedule.getSession().getPatient())){
//                patientSet.add(schedule.getSession().getPatient());
            scheduleDTOS.add(ScheduleDTO.toModel(schedule));
            //}
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
            if(model.getConclusion()!=null) {
                for (int i = 0; i < model.getConclusion().size(); i++) {
                    JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                    float indicatorValue = -1;
                    if (conclusion.has("Название")) {
                        if(!conclusion.get("Значение").getAsString().isEmpty()) {
                            indicatorValue = conclusion.get("Значение").getAsFloat();
                            if (indicatorValue < conclusion.get("Минимально допустимое значение").getAsFloat() ||
                                    indicatorValue > conclusion.get("Максимальное допустимое значение").getAsFloat()) {
                                conclusion.addProperty("Норма", "нет");
                            } else {
                                conclusion.addProperty("Норма", "да");
                            }
                        }
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
            if(model.getConclusion()!=null) {
                for (int i = 0; i < model.getConclusion().size(); i++) {
                    JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                    if (conclusion.has("Название")) {
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
        }
        return analysis;
    }

    public ScheduleDTO addSessionToSchedule(Long id, Long session){
        scheduleRepo.addSession(id, session);
        return ScheduleDTO.toModel(scheduleRepo.findScheduleById(id));
    }

    public void update(ScheduleDTO dto){
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setDate(dto.getDate());
        schedule.setEmployee(EmployeeDTO.toEntity(dto.getEmployee()));
        if(dto.getSession()!=null) {
            schedule.setSession(SessionDTO.toEntity(dto.getSession()));
        }
        else {
            schedule.setSession(null);
        }
        scheduleRepo.save(schedule);
    }

    public ScheduleDTO findById(Long id){
        return ScheduleDTO.toModel(scheduleRepo.findScheduleById(id));
    }

    public List<ScheduleDTO> findAllDateOrder() {
        List<ScheduleDTO> dtos = new ArrayList<>();
        List<Schedule> entities = (List<Schedule>) scheduleRepo.findAllOrderByDateDesc();
        Set<Patient> patientSet = new HashSet<>();
        for (Schedule entity : entities) {
            if(!patientSet.contains(entity.getSession().getPatient())){
                dtos.add(ScheduleDTO.toModel(entity));
                patientSet.add(entity.getSession().getPatient());
            }
        }
        return dtos;
    }

    public List<String> getPrescription(Long id) {
        List<String> prescriptions = new ArrayList<>();
        List<ScheduleDTO> schedules = this.getByPatient(id);
        for (ScheduleDTO schedule : schedules) {
            SessionDTO model = schedule.getSession();
            if (model.getConclusion() != null) {
                for (int i = 0; i < model.getConclusion().size(); i++) {
                    JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                    if (conclusion.has("Название поля")) {
                        if(conclusion.get("Название поля").getAsString().equals("Назначения") &&
                                !conclusion.get("Значение").getAsString().isEmpty()){
                            prescriptions.add(conclusion.get("Значение").getAsString() + "; Дата " + schedule.getDate());
                            break;
                        }
                    }
                }
            }
        }
        return prescriptions;
    }
}
