package ru.platonova.medmod.DTO;

import lombok.*;
import ru.platonova.medmod.entity.EmployeeRole;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDTO {
    private Long id;
    private String name;

    public static EmployeeRole toEntity(RoleDTO model){
        return new EmployeeRole(
                model.getId(),
                model.getName()
        );
    }

    public static RoleDTO toModel(EmployeeRole role){
        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }
}
