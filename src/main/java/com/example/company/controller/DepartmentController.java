package com.example.company.controller;

import com.example.company.dto.DepartmentDto;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/section")
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("/section/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(departmentService.getDepartmentById(id) , HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentDto> getting(@RequestBody DepartmentDto dto){
        return ResponseEntity.ok(departmentService.createDepart(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DepartmentDto> UpdateDepartment(@PathVariable int id , @RequestBody DepartmentDto dto){
        DepartmentDto dto1 = departmentService.updateDepartment(id , dto);
        return ResponseEntity.ok(dto1);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAll(){
        departmentService.DeleteAll();
        return ResponseEntity.ok("Deleted All was Successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") int id){
        departmentService.DeleteDepartmentById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
