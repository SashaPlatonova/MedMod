package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Schedule;

import java.sql.Date;
import java.util.List;

public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByDate(Date date);

    @Query("select sch from Schedule sch join fetch Employee emp on" +
            "(sch.session.id=emp.id) where emp.surName =:surName")
    List<Schedule> findAllByEmployee(String surName);
}
