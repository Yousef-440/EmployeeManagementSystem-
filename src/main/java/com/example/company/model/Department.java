package com.example.company.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String d_Name;
    private String d_Manager;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Employees> employees = new ArrayList<>();


    public Department() {
    }

    public Department(int id, String d_Name, String d_Manager) {
        Id = id;
        this.d_Name = d_Name;
        this.d_Manager = d_Manager;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getD_Manager() {
        return d_Manager;
    }

    public void setD_Manager(String d_Manager) {
        this.d_Manager = d_Manager;
    }
}
