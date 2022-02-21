package ru.platonova.medmod.DTO;


import lombok.*;
import ru.platonova.medmod.entity.Diagnosis;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiagnosisDTO {
    private Long id;
    private String name;
    private String code;
    private StandartDTO standart;

    public static DiagnosisDTO toModel(Diagnosis diagnosis){
        return new DiagnosisDTO(diagnosis.getId(),
                diagnosis.getName(),
                diagnosis.getCode(),
                StandartDTO.toModel(diagnosis.getStandart())
        );
    }

    public static Diagnosis toEntity(DiagnosisDTO dto){
        return new Diagnosis(dto.getId(),
        dto.getName(),
                dto.getCode(),
                StandartDTO.toEntity(dto.getStandart()));
    }
}
