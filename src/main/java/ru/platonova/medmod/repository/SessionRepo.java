
package ru.platonova.medmod.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Session;

import java.util.List;

public interface SessionRepo extends CrudRepository<Session, Long> {

    @Query(value = """
    select * from session inner join schedule s on session.id = s.session_id
    where s.id :=id
    """, nativeQuery = true)
    Session findByScheduleId(Long id);

    @Query(value = "select * from session join patient p on session.patient_id = p.id" +
            " where p.id =:id", nativeQuery = true)
    List<Session> findByPatient(Long id);


    @Query(value = "select * from session join schedule s on session.id = s.session_id " +
            "join employee e on s.employee_id = e.id where e.sur_name =:surName", nativeQuery = true)
    List<Session> findByEmployee(String surName);

    @Query(value = "select * from session join session_cat sc " +
            "on session.category_id = sc.id where sc.name =:category", nativeQuery = true)
    List<Session> findByCategoryName(String category);

    Session findSessionById(Long id);
}
