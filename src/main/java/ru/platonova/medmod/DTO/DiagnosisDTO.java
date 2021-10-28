package ru.platonova.medmod.DTO;


import lombok.*;
import ru.platonova.medmod.entity.Diagnosis;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiagnosisDTO {
    private String name;
    private String code;
    private StandartDTO standart;

    public static DiagnosisDTO toModel(Diagnosis diagnosis){
        return new DiagnosisDTO(
                diagnosis.getName(),
                diagnosis.getCode(),
                StandartDTO.toModel(diagnosis.getStandart())
        );
    }
}
