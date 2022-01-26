package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.platonova.medmod.entity.EmployeeRole;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<EmployeeRole, Long> {

    Optional<EmployeeRole> findByRoleName(String name);
}
