package com.example.company.repository;

import com.example.company.model.Department;
import com.example.company.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends JpaRepository<Employees , Integer> {
    List<Employees> findByDepartmentId(int departmentId);
}
