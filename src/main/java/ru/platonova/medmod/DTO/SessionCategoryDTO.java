package ru.platonova.medmod.DTO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
    private JsonArray structure;

    public static JsonArray toJson(String structure){
        Gson g = new Gson();
        return g.fromJson(structure, JsonArray.class);
    }

    public static SessionCategoryDTO toModel(SessionCategory category){
        return new SessionCategoryDTO(category.getId(),
                category.getName(),
                toJson(category.getStructure())
        );
    }

}
