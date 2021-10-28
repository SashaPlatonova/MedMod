package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.DocStandart;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StandartDTO {
    private String docName;

    public static StandartDTO toModel(DocStandart standart){
        return new StandartDTO(standart.getDocName());
    }
}
