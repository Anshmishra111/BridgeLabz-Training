package com.firstspringapp.component;

import org.springframework.stereotype.Component;

@Component
public class DepartmentBean {

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}