package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Department;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO {
    private Long id;
    private String name;

    public static Department toEntity(DepartmentDTO model){
        return new Department(
                model.getId(),
                model.getName()
        );
    }

    public static DepartmentDTO toModel(Department department){
        return new DepartmentDTO(department.getId(), department.getName());
    }

}
