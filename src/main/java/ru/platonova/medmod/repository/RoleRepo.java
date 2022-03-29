package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.platonova.medmod.entity.ERole;
import ru.platonova.medmod.entity.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
