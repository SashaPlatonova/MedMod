package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.EmployeeRole;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {
    private String name;

    public static RoleDTO toModel(EmployeeRole role){
        return new RoleDTO(role.getRoleName());
    }
}
