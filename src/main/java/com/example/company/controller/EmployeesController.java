package com.example.company.controller;

import com.example.company.dto.EmployeesDto;
import com.example.company.dto.EmployeesResponse;
import com.example.company.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;


    @GetMapping("All")
    public ResponseEntity<EmployeesResponse> getAllEmployees(
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) int pageNo,
            @RequestParam(value = "pageSize" , defaultValue = "10" , required = false) int pageSize

    ){
        return ResponseEntity.ok(employeesService.getAllEmployees(pageNo , pageSize));
    }

    @GetMapping("/employeeId/{employeesId}")
    public ResponseEntity<EmployeesDto> getEmpById(@PathVariable("employeesId") int employeesId){
        return ResponseEntity.ok(employeesService.getEmpById(employeesId));
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<EmployeesDto>> getEmployeesByDepartmentId(@PathVariable("id") int id){
        return ResponseEntity.ok(employeesService.getEmployeesByDepartmentId(id));
    }

    @GetMapping("department/{departmentId}/employee/{employeeId}")
    public ResponseEntity<EmployeesDto> getEmployeesById(@PathVariable("departmentId") int departmentId , @PathVariable("employeeId") int employeeId){
        return ResponseEntity.ok(employeesService.getEmployeesById(departmentId , employeeId));
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<EmployeesDto> AddEmployee(@PathVariable int id , @RequestBody EmployeesDto employeesDto){
        return ResponseEntity.ok(employeesService.createEmployee(id , employeesDto));
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<EmployeesDto> UpdatedEmployee(@PathVariable("employeeId") int employeeId , @RequestBody EmployeesDto employeesDto){
        return ResponseEntity.ok(employeesService.updateEmployee(employeeId , employeesDto));
    }

    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("employeeId") int employeeId){
        employeesService.DeleteEmployeeById(employeeId);
        String str = "Employee By {" + employeeId + "} ID Was deleted successfully";
        return ResponseEntity.ok(str);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllEmployee(){
        employeesService.DeleteAllEmployee();
        return ResponseEntity.ok("All employees have been successfully deleted.");
    }

}
