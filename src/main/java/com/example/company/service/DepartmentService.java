package com.example.company.service;

import com.example.company.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepart(DepartmentDto dto);

    List<DepartmentDto> getAllDepartment();

    DepartmentDto getDepartmentById(int id);

    DepartmentDto updateDepartment(int id , DepartmentDto dto);

    void DeleteAll();

    void DeleteDepartmentById(int id);
}
