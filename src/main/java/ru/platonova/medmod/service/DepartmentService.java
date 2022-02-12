package ru.platonova.medmod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.platonova.medmod.DTO.DepartmentDTO;
import ru.platonova.medmod.entity.Department;
import ru.platonova.medmod.repository.DepartmentRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public List<DepartmentDTO> getAll(){
        List<Department> entities = (List<Department>) departmentRepo.findAll();
        List<DepartmentDTO> models = new ArrayList<>();
        for (Department entity : entities) {
            models.add(DepartmentDTO.toModel(entity));
        }
        return models;
    }
}
