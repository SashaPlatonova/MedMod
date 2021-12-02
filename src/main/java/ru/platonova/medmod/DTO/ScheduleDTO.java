package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Schedule;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDTO {
    private Long id;
    private Date date;
    private Time time;
    private EmployeeDTO employee;
    private SessionDTO session;

    public static ScheduleDTO toModel(Schedule schedule){
        ScheduleDTO model = new ScheduleDTO();
        model.setId(schedule.getId());
        model.setDate(schedule.getDate());
        model.setTime(schedule.getTime());
        model.setEmployee(EmployeeDTO.toModel(schedule.getEmployee()));
        if(schedule.getSession()!=null){
            model.setSession(SessionDTO.toModel(schedule.getSession()));
        }
        else {
            model.setSession(null);
        }
        return model;
    }

}
