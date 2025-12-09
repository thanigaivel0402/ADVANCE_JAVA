package com.example.employyepayrollJdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;





public class EmployeePayrollDBService {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_service";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private static EmployeePayrollDBService instance;

    EmployeePayrollDBService() {}

    public static EmployeePayrollDBService getInstance() {
        if (instance == null)
            instance = new EmployeePayrollDBService();
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded!");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Established!");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found!");
        }
        return connection;
    }

    public void readData() {
        String query = "SELECT * FROM employee_payroll;";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                Date start = rs.getDate("start");
                System.out.println(id + " | " + name + " | " + salary + " | " + start);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void updateSalary(String name, double newSalary) {
        String query = String.format("UPDATE employee_payroll SET salary = %.2f WHERE name = '%s';", newSalary, name);
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            int rows = stmt.executeUpdate(query);
            if (rows > 0) {
                System.out.println("Salary updated successfully for " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalaryUsingPreparedStatement(String name, double newSalary) {
        String query = "UPDATE employee_payroll SET salary = ? WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, newSalary);
            pstmt.setString(2, name);
            int rows = pstmt.executeUpdate();
            if (rows > 0)
                System.out.println("Salary updated using PreparedStatement for " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getEmployeesByDateRange(LocalDate start, LocalDate end) {
        String query = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDate(1, Date.valueOf(start));
            pstmt.setDate(2, Date.valueOf(end));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " joined on " + rs.getDate("start_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSalaryStatisticsByGender() {
        String query = "SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) FROM employee_payroll GROUP BY gender;";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Gender: " + rs.getString(1)
                        + " | SUM: " + rs.getDouble(2)
                        + " | AVG: " + rs.getDouble(3)
                        + " | MIN: " + rs.getDouble(4)
                        + " | MAX: " + rs.getDouble(5)
                        + " | COUNT: " + rs.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public EmployeePayroll addEmployee(String name, String gender, double salary, LocalDate startDate) throws SQLException {
        String query = "INSERT INTO employee_payroll (name, gender, salary, start,) VALUES (?, ?, ?, ?)";
        EmployeePayroll employee = null;
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setDouble(3, salary);
            pstmt.setDate(4, Date.valueOf(startDate));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    employee = new EmployeePayroll(id, name, salary, startDate);
                    System.out.println("Employee added successfully: " + employee);
                }
            } else {
                throw new SQLException("Insertion failed!");
            }
        }
        return employee;
    }

    public void addEmployeeWithPayroll(String name, String gender, double salary, LocalDate startDate) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            // Insert into employee_payroll
            String empQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date) VALUES (?, ?, ?, ?)";
            PreparedStatement empStmt = connection.prepareStatement(empQuery, Statement.RETURN_GENERATED_KEYS);
            empStmt.setString(1, name);
            empStmt.setString(2, gender);
            empStmt.setDouble(3, salary);
            empStmt.setDate(4, Date.valueOf(startDate));
            empStmt.executeUpdate();

            ResultSet rs = empStmt.getGeneratedKeys();
            int empId = 0;
            if (rs.next()) empId = rs.getInt(1);

            // Derived payroll details
            double deductions = salary * 0.2;
            double taxablePay = salary - deductions;
            double tax = taxablePay * 0.1;
            double netPay = salary - tax;

            String payQuery = "INSERT INTO payroll_details (employee_id, basic_pay, deductions, taxable_pay, tax, net_pay) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement payStmt = connection.prepareStatement(payQuery);
            payStmt.setInt(1, empId);
            payStmt.setDouble(2, salary);
            payStmt.setDouble(3, deductions);
            payStmt.setDouble(4, taxablePay);
            payStmt.setDouble(5, tax);
            payStmt.setDouble(6, netPay);
            payStmt.executeUpdate();

            connection.commit();
            System.out.println("Employee + Payroll details added successfully!");

        } catch (SQLException e) {
            if (connection != null) {
                System.out.println("Transaction rolled back!");
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String query = "DELETE FROM employee_payroll WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "No record found.");
        }
    }

    public void addEmployeeFullTransaction(String name, String gender, double salary,
        LocalDate startDate, List<String> deptNames) throws SQLException {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);


			String empQuery = "INSERT INTO employee_payroll (name, gender, salary, start_date) VALUES (?, ?, ?, ?)";
			PreparedStatement empStmt = connection.prepareStatement(empQuery, Statement.RETURN_GENERATED_KEYS);
			empStmt.setString(1, name);
			empStmt.setString(2, gender);
			empStmt.setDouble(3, salary);
			empStmt.setDate(4, Date.valueOf(startDate));
			empStmt.executeUpdate();
			ResultSet rs = empStmt.getGeneratedKeys();
			rs.next();
			int empId = rs.getInt(1);


			double deductions = salary * 0.2;
			double taxable = salary - deductions;
			double tax = taxable * 0.1;
			double net = salary - tax;

			String payQuery = "INSERT INTO payroll_details (employee_id, basic_pay, deductions, taxable_pay, tax, net_pay) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement payStmt = connection.prepareStatement(payQuery);
			payStmt.setInt(1, empId);
			payStmt.setDouble(2, salary);
			payStmt.setDouble(3, deductions);
			payStmt.setDouble(4, taxable);
			payStmt.setDouble(5, tax);
			payStmt.setDouble(6, net);
			payStmt.executeUpdate();


			for (String dept : deptNames) {
				PreparedStatement deptStmt = connection.prepareStatement(
						"INSERT INTO employee_department (employee_id, dept_id) VALUES (?, (SELECT dept_id FROM department WHERE dept_name = ?))");
				deptStmt.setInt(1, empId);
				deptStmt.setString(2, dept);
				deptStmt.executeUpdate();
			}

			connection.commit();
			System.out.println("Employee added successfully across all tables.");

		} catch (SQLException e) {
			if (connection != null)
				connection.rollback();
			throw e;
		} finally {
			if (connection != null)
				connection.setAutoCommit(true);
		}
	}

    
    public void removeEmployee(String name) {
        String query = "UPDATE employee_payroll SET is_active = FALSE WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Employee set inactive: " + name : "Employee not found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Connected to Database: " + connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
