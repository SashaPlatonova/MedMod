package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
