package ru.platonova.medmod.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.SessionCategoryDTO;
import ru.platonova.medmod.DTO.SessionDTO;
import ru.platonova.medmod.entity.Session;
import ru.platonova.medmod.entity.SessionCategory;
import ru.platonova.medmod.repository.CategoryRepo;
import ru.platonova.medmod.repository.SessionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SessionService {

    private final SessionRepo sessionRepo;
    private final CategoryRepo categoryRepo;

    public SessionService(SessionRepo sessionRepo, CategoryRepo categoryRepo) {
        this.sessionRepo = sessionRepo;
        this.categoryRepo = categoryRepo;
    }

    public SessionDTO findById(Long id){
        return SessionDTO.toModel(sessionRepo.findSessionById(id));
    }

    public List<SessionDTO> getByPatient(Long id){
        List<SessionDTO> models = new ArrayList<>();
        List<Session> sessions = sessionRepo.findByPatient(id);
        for (Session session : sessions) {
            models.add(SessionDTO.toModel(session));
        }
        for (SessionDTO model : models) {
            for (int i = 0; i<model.getConclusion().size(); i++){
                JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                float indicatorValue = -1;
                if(conclusion.has("Название")){
                    indicatorValue = conclusion.get("Значение").getAsFloat();
                    if(indicatorValue<conclusion.get("Минимально допустимое значение").getAsFloat() ||
                            indicatorValue>conclusion.get("Максимальное допустимое значение").getAsFloat()) {
                        conclusion.addProperty("Норма", false);
                    }
                }
            }
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

    public SessionDTO getBySchedule(Long id){
        return SessionDTO.toModel(sessionRepo.findByScheduleId(id));
    }

    public JsonArray getAnalysisChanges(Long id, String indicatorName){
        List<SessionDTO> models = new ArrayList<>();
        List<Session> sessions = sessionRepo.findByPatient(id);
        String gender = sessions.get(0).getPatient().getGender();
        JsonArray resArray = new JsonArray();
        for (Session session : sessions) {
            models.add(SessionDTO.toModel(session));
        }
        for (SessionDTO model : models) {
            for (int i = 0; i<model.getConclusion().size(); i++){
                JsonObject conclusion = model.getConclusion().get(i).getAsJsonObject();
                float indicatorValue = -1;
                if(conclusion.has("Название") && conclusion.get("Название").getAsString().equals(indicatorName)){
                    indicatorValue = conclusion.get("Значение").getAsFloat();
                    if(indicatorValue<conclusion.get("Минимально допустимое значение").getAsFloat() ||
                            indicatorValue>conclusion.get("Максимальное допустимое значение").getAsFloat()) {
                        conclusion.addProperty("Норма", false);
                    }
                    resArray.add(conclusion);
                }
            }
        }
        return resArray;
    }

    public void deleteSession(Long id){
        sessionRepo.deleteById(id);
    }

    public SessionDTO addSession(SessionDTO dto){
        return SessionDTO.toModel(sessionRepo.save(SessionDTO.toEntity(dto)));
    }

    public List<SessionCategoryDTO> getAllCatNames(){
        List<SessionCategory> entities = (List<SessionCategory>) categoryRepo.findAll();
        List<SessionCategoryDTO> models = new ArrayList<>();
        for (SessionCategory entity : entities) {
            models.add(SessionCategoryDTO.toModel(entity));
        }
        return models;
    }

    public Long addSessionId(SessionDTO session){
//         session.setConclusion(session.getCategory().getStructure());
        return sessionRepo.save(SessionDTO.toEntity(session)).getId();
    }

    public void updateSession(SessionDTO dto){
        sessionRepo.save(SessionDTO.toEntity(dto));
    }

}

