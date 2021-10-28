package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.SessionCategory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SessionCategoryDTO {
    private String name;
    private String structure;

    public static SessionCategoryDTO toModel(SessionCategory category){
        return new SessionCategoryDTO(
                category.getName(),
                category.getStructure()
        );
    }

}
