package com.example.employyepayrollJdbc;

import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeePayrollMain {
    public static void main(String[] args) throws SQLException {
        EmployeePayrollDBService dbService = new EmployeePayrollDBService();
        dbService.readData();
        dbService.updateSalary("Terisa", 4000000.00);
        EmployeePayroll newEmp = dbService.addEmployee("John", "M", 600000.0, LocalDate.now());


    }
}
