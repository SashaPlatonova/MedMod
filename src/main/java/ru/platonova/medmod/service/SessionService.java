package ru.platonova.medmod.service;

import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.entity.Session;
import ru.platonova.medmod.repository.SessionRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionService {

    private SessionRepo sessionRepo;

    public SessionService(SessionRepo sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    public List<SessionDTO> getByPatient(String snils){
        List<SessionDTO> models = new ArrayList<>();
        List<Session> sessions = sessionRepo.findByPatient(snils);
        for (Session session : sessions) {
            models.add(SessionDTO.toModel(session));
        }
        return models;
    }

    public List<SessionDTO> getByEmployee(String surName){
        List<SessionDTO> models = new ArrayList<>();
        List<Session> sessions = sessionRepo.findByEmployee(surName);
        for (Session session : sessions) {
            models.add(SessionDTO.toModel(session));
        }
        return models;
    }

    public List<SessionDTO> getByCategory(String category){
        List<SessionDTO> models = new ArrayList<>();
        List<Session> sessions = sessionRepo.findByCategoryName(category);
        for (Session session : sessions) {
            models.add(SessionDTO.toModel(session));
        }
        return models;
    }
}
