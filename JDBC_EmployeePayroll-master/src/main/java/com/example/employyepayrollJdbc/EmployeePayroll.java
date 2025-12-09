package com.example.employyepayrollJdbc;


import java.time.LocalDate;
import java.util.List;

public class EmployeePayroll {
    public int id;
    public String name;
    public double salary;
    public LocalDate startDate;
    
    private List<String> departments;  


    public EmployeePayroll(int id, String name, double salary, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "EmployeePayroll [id=" + id + ", name=" + name + ", salary=" + salary + ", startDate=" + startDate + "]";
    }
}

