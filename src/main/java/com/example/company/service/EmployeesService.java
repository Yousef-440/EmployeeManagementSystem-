package com.example.company.service;

import com.example.company.dto.EmployeesDto;
import com.example.company.dto.EmployeesResponse;

import java.util.List;

public interface EmployeesService {
    EmployeesDto createEmployee(int departmentId , EmployeesDto dto);

    EmployeesResponse getAllEmployees(int pageNo , int pageSize);

    List<EmployeesDto> getEmployeesByDepartmentId(int id);

    EmployeesDto getEmployeesById(int departmentId , int employeesId);

    EmployeesDto getEmpById(int employeeId);

    EmployeesDto updateEmployee(int employeeId , EmployeesDto employeesDto);

    void DeleteEmployeeById(int id);

    void DeleteAllEmployee();
}
