package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.platonova.medmod.entity.Department;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {

}
