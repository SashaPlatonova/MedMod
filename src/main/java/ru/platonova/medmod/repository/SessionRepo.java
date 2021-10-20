package ru.platonova.medmod.repository;

import org.springframework.data.repository.CrudRepository;
import ru.platonova.medmod.entity.Session;

public interface SessionRepo extends CrudRepository<Session, Long> {
}
