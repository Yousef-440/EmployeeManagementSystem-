package com.example.company.service.imp;

import com.example.company.Exception.DepartmentNotFound;
import com.example.company.dto.DepartmentDto;
import com.example.company.model.Department;
import com.example.company.repository.DepartmentRepo;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepo repo;

    @Override
    public DepartmentDto createDepart(DepartmentDto dto) {
        Department depart = new Department();

        depart.setD_Name(dto.getD_Name());
        depart.setD_Manager(dto.getD_Manager());

        Department newDepart = repo.save(depart);

        return mapToDto(newDepart);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments = repo.findAll();
        return departments.stream().map(dep -> mapToDto(dep)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(int id) {
        Department department = repo.findById(id).orElseThrow(()->new DepartmentNotFound("Sorry, Department Not Found"));
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(int id, DepartmentDto dto) {
        Department department = repo.findById(id).orElseThrow(()->new DepartmentNotFound("Sorry, Department Not Found"));
        department.setD_Name(dto.getD_Name());
        department.setD_Manager(dto.getD_Manager());

        Department updated = repo.save(department);
        return mapToDto(updated);
    }

    @Override
    public void DeleteAll() {
        repo.deleteAll();
    }

    @Override
    public void DeleteDepartmentById(int id) {
        Department department = repo.findById(id).orElseThrow(()->new DepartmentNotFound("Sorry, Department Not Found"));
        repo.deleteById(id);
    }

    private DepartmentDto mapToDto(Department depart) {
        DepartmentDto dto = new DepartmentDto();
        dto.setD_ID(depart.getId());
        dto.setD_Name(depart.getD_Name());
        dto.setD_Manager(depart.getD_Manager());
        return dto;
    }
}
