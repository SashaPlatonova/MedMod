package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Patient;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDTO {

    private String snils;
    private String address;
    private String insurancePolicy;

    public static PatientDTO toModel(Patient patient){
        return new PatientDTO(
                patient.getSnils(),
                patient.getAddress(),
                patient.getInsurancePolicy()
        );
    }
}
