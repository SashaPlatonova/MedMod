package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.platonova.medmod.entity.SessionCategory;

@Repository
public interface CategoryRepo extends CrudRepository<SessionCategory, Long> {
}
