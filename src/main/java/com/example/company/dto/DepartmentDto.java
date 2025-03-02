package com.example.company.dto;


public class DepartmentDto {

    private int d_ID;
    private String d_Name;
    private String d_Manager;

    public DepartmentDto() {
    }

    public DepartmentDto(int d_ID, String d_Name, String d_Manager) {
        this.d_ID = d_ID;
        this.d_Name = d_Name;
        this.d_Manager = d_Manager;
    }

    public int getD_ID() {
        return d_ID;
    }

    public void setD_ID(int d_ID) {
        this.d_ID = d_ID;
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
