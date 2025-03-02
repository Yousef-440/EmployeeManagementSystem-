package com.example.company.service.imp;

import com.example.company.Exception.DepartmentNotFound;
import com.example.company.Exception.EmployeeNotFound;
import com.example.company.dto.DepartmentDto;
import com.example.company.dto.EmployeesDto;
import com.example.company.dto.EmployeesResponse;
import com.example.company.model.Department;
import com.example.company.model.Employees;
import com.example.company.repository.DepartmentRepo;
import com.example.company.repository.EmployeesRepo;
import com.example.company.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceImp implements EmployeesService {

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeesRepo employeesRepo;

    @Override
    public EmployeesDto createEmployee(int departmentId, EmployeesDto dto) {
        Employees employees = mapToEntity(dto);
        Department department = departmentRepo.findById(departmentId).orElseThrow(()->new DepartmentNotFound("Sorry, Department Id not Found"));

        employees.setDepartment(department);

        employeesRepo.save(employees);
        return mapToDto(employees);
    }

    @Override
    public EmployeesResponse getAllEmployees(int pageNo , int pageSize) {
        Pageable pageable = PageRequest.of(pageNo , pageSize);
        Page<Employees> employees = employeesRepo.findAll(pageable);
        List<Employees> listOfEmployee = employees.getContent();
        if(employees.isEmpty()){
            throw new EmployeeNotFound("Sorry, did not find any employee");
        }
        List<EmployeesDto>content =listOfEmployee.stream().map(E -> mapToDto(E)).collect(Collectors.toList());
        EmployeesResponse employeesResponse =new EmployeesResponse();
        employeesResponse.setContent(content);
        employeesResponse.setPageNo(pageNo);
        employeesResponse.setPageSize(pageSize);
        employeesResponse.setTotalElements(employees.getTotalElements());
        employeesResponse.setTotalPages(employees.getTotalPages());
        employeesResponse.setLast(employees.isLast());

        return employeesResponse;
    }

    @Override
    public List<EmployeesDto> getEmployeesByDepartmentId(int id) {
        List<Employees> employees = employeesRepo.findByDepartmentId(id);
        return employees.stream()
                .map(emp -> mapToDto(emp))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeesDto getEmployeesById(int departmentId, int employeesId) {
        Department department = departmentRepo.findById(departmentId).orElseThrow(()->new DepartmentNotFound("Sorry, DepartmentId not Found"));
        Employees employees = employeesRepo.findById(employeesId).orElseThrow(()->new EmployeeNotFound("Sorry, Employee Not Found"));

        if(employees.getDepartment().getId() != department.getId()){
            throw new EmployeeNotFound("Sorry, Not Found");
        }
        return mapToDto(employees);
    }

    @Override
    public EmployeesDto getEmpById(int employeeId) {
        Employees employees =  employeesRepo.findById(employeeId).orElseThrow(()->new EmployeeNotFound("Sorry, employees Id not found"));
        return mapToDto(employees);
    }

    @Override
    public EmployeesDto updateEmployee(int employeeId, EmployeesDto employeesDto) {
        Employees employees = employeesRepo.findById(employeeId).orElseThrow(()->new EmployeeNotFound("Sorry, EmployeeId not Found"));

        employees.setFirstName(employeesDto.getFirstName());
        employees.setLastName(employeesDto.getLastName());
        employees.setEmail(employeesDto.getEmail());
        employees.setAddress(employeesDto.getAddress());

        Employees updated = employeesRepo.save(employees);

        return mapToDto(updated);
    }

    @Override
    public void DeleteEmployeeById(int id) {
        Employees employees = employeesRepo.findById(id).orElseThrow(()->new EmployeeNotFound("Sorry, Employee not Found"));
        employeesRepo.deleteById(id);
    }

    @Override
    public void DeleteAllEmployee() {
        if(employeesRepo.count() == 0){
            throw new EmployeeNotFound("There are no staff to delete.");
        }
        employeesRepo.deleteAll();
    }

    private EmployeesDto mapToDto(Employees employees) {
        EmployeesDto dto = new EmployeesDto();
        dto.setId(employees.getId());
        dto.setFirstName(employees.getFirstName());
        dto.setLastName(employees.getLastName());
        dto.setEmail(employees.getEmail());
        dto.setAddress(employees.getAddress());

        return dto;
    }

    private Employees mapToEntity(EmployeesDto employeesDto){
        Employees employees = new Employees();
        employees.setId(employeesDto.getId());
        employees.setFirstName(employeesDto.getFirstName());
        employees.setLastName(employeesDto.getLastName());
        employees.setEmail(employeesDto.getEmail());
        employees.setAddress(employeesDto.getAddress());
        return employees;
    }
}
