package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.Department;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDTO {
    private String name;

    public static DepartmentDTO toModel(Department department){
        return new DepartmentDTO(department.getName());
    }

}
