package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleRepo extends CrudRepository<Schedule, Long> {

    @Query(value = """
        select * from schedule inner join employee 
        on schedule.employee_id = employee.id 
        where employee_id =:id and date =:date
    """, nativeQuery = true)
    List<Schedule> findAllByDateAndEmployee(Date date, Long id);

    @Query("select sch from Schedule sch join fetch Employee emp on" +
            "(sch.employee.id=emp.id) where emp.surName =:surName")
    List<Schedule> findAllByEmployee(String surName);

    @Query(value = "select * from schedule inner join employee " +
            "on schedule.employee_id = employee.id where employee.sur_name=:surName and " +
            "(SELECT EXTRACT(WEEK FROM (select current_date))) in  (SELECT EXTRACT(WEEK FROM (schedule.date)));",
            nativeQuery = true)
    List<Schedule> findAllToWeek(String surName);

    @Query(value = """
        select * from schedule inner join session s on s.id = schedule.session_id 
        inner join diagnosis d on d.id = s.diagnosis_id inner join patient p on p.id = s.patient_id
        where patient_id =:id order by date desc limit 1;
    """,
    nativeQuery = true)
    Schedule findLastByPatient(Long id);

    @Query(value = """
        select * from schedule inner join session s on s.id = schedule.session_id 
     inner join patient p on p.id = s.patient_id
        where patient_id =:id
    """,
            nativeQuery = true)
    List<Schedule> findByPatient(Long id);
}
