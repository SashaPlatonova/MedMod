package ru.platonova.medmod.DTO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.*;
import ru.platonova.medmod.entity.Session;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionDTO {

    private Long id;
    private String sessionName;
    private int office;
    private SessionCategoryDTO category;
    private DiagnosisDTO diagnosis;
    private PatientDTO patient;
    private JsonArray conclusion;

    public static JsonArray toJson(String conclusion){
        Gson g = new Gson();
        return g.fromJson(conclusion, JsonArray.class);
    }

    public static String fromJsonToString(JsonArray json){
        Gson g = new Gson();
        return g.toJson(json);
    }

    public static SessionDTO toModel(Session session){
        SessionDTO model = new SessionDTO();
        model.setId(session.getId());
        model.setSessionName(session.getSessionName());
        model.setCategory(SessionCategoryDTO.toModel(session.getCategory()));
        model.setOffice(session.getOffice());
        if(session.getDiagnosis()!=null){
            model.setDiagnosis(DiagnosisDTO.toModel(session.getDiagnosis()));
        }
        else {
            model.setDiagnosis(null);
        }
        if(session.getPatient()!=null){
            model.setPatient(PatientDTO.toModel(session.getPatient()));
        }
        else {
            model.setPatient(null);
        }
        if(session.getConclusion()!=null) {
            model.setConclusion(toJson(session.getConclusion()));
        }
        else{
            model.setConclusion(null);
        }
        return model;
    }

    public static Session toEntity(SessionDTO dto){
        Session session = new Session();
        if(dto.getId()!=null){
            session.setId(dto.getId());
        }
        session.setSessionName(dto.getSessionName());
        if(dto.getDiagnosis()!=null){
            session.setDiagnosis(session.getDiagnosis());
        }
        else {
            session.setDiagnosis(null);
        }
        if(dto.getPatient()!=null){
            session.setPatient(PatientDTO.toEntity(dto.getPatient()));
        }
        session.setOffice(dto.getOffice());
        if(dto.getConclusion()!=null){
            session.setConclusion(fromJsonToString(dto.getConclusion()));
        }
        else {
            session.setConclusion(null);
        }
        if(dto.getCategory()!=null){
            session.setCategory(SessionCategoryDTO.toEntity(dto.getCategory()));
        }
        else {
            session.setCategory(null);
        }

        return session;
    }
}
