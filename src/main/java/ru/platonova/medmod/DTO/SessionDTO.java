package ru.platonova.medmod.DTO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.*;
import ru.platonova.medmod.entity.Session;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionDTO {
    private String sessionName;
    private int office;
    private SessionCategoryDTO category;
    private DiagnosisDTO diagnosis;
    private PatientDTO patient;
    private JsonObject conclusion;

    public static JsonObject toJson(String conclusion){
        Gson g = new Gson();
        return g.fromJson(conclusion, JsonObject.class);
    }

    public static SessionDTO toModel(Session session){
        return new SessionDTO(
                session.getSessionName(),
                session.getOffice(),
                SessionCategoryDTO.toModel(session.getCategory()),
                DiagnosisDTO.toModel(session.getDiagnosis()),
                PatientDTO.toModel(session.getPatient()),
                toJson(session.getConclusion())
        );
    }
}
