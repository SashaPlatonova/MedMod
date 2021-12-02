package ru.platonova.medmod.DTO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import lombok.*;
import ru.platonova.medmod.entity.SessionCategory;

import java.io.StringReader;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionCategoryDTO {
    private Long id;
    private String name;
    private JsonObject structure;

    public static JsonObject toJson(String structure){
        Gson g = new Gson();
//        JsonReader reader = new JsonReader(new StringReader(structure));
//        reader.setLenient(true);
        return g.fromJson(structure, JsonObject.class);
    }

    public static SessionCategoryDTO toModel(SessionCategory category){
        return new SessionCategoryDTO(category.getId(),
                category.getName(),
                toJson(category.getStructure())
        );
    }

}
