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
        model.setConclusion(toJson(session.getConclusion()));
        return model;
    }
}
