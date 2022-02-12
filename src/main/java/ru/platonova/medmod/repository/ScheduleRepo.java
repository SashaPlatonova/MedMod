package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
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

    @Query("select sch from Schedule sch join fetch Employee emp on" +
            "(sch.employee.id=emp.id) where emp.id =:id")
    List<Schedule> findAllByEmployeeID(Long id);

    @Query(value = "select * from schedule inner join employee " +
            "on schedule.employee_id = employee.id where employee.sur_name=:surName and " +
            "(SELECT EXTRACT(WEEK FROM (select current_date))) in  (SELECT EXTRACT(WEEK FROM (schedule.date)));",
            nativeQuery = true)
    List<Schedule> findAllToWeek(String surName);

    @Query(value = """
        select * from schedule inner join session s on s.id = schedule.session_id
                left join diagnosis d on d.id = s.diagnosis_id inner join patient p on p.id = s.patient_id
                where employee_id =:id order by date desc;
    """,
    nativeQuery = true)
    List<Schedule> findLastByEmployee(Long id);

    @Query(value = """
        select * from schedule inner join session s on s.id = schedule.session_id 
     inner join patient p on p.id = s.patient_id
        where patient_id =:id order by date desc
    """,
            nativeQuery = true)
    List<Schedule> findByPatient(Long id);

    Schedule findScheduleById(Long id);

    @Query(value = """
    select * from schedule inner join session s on s.id = schedule.session_id 
    left join diagnosis d on d.id = s.diagnosis_id 
    inner join patient p on p.id = s.patient_id order by date desc;
    """, nativeQuery = true)
    List<Schedule> findAllOrderByDateDesc();

    @Transactional
    @Modifying
    @Query(value = """
    update schedule set session_id=:session where id =:id
    """, nativeQuery = true)
    void addSession(Long id, Long session);
}
