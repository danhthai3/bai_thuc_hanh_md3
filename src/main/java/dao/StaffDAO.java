package dao;

import staff.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/staffquiz?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "1234%Giang";
    private Connection jdbcConnection;

    public StaffDAO() {
    }

    private void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    private void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    public List<Staff> getStaffByName(String keyword) throws SQLException {
        List<Staff> staffList = new ArrayList<>();
        connect();
        PreparedStatement preparedStatement = jdbcConnection.prepareStatement("SELECT * FROM staff WHERE name LIKE ?");
        preparedStatement.setString(1, "%" + keyword + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Staff staff = new Staff();
            staff.setId(resultSet.getInt("id"));
            staff.setName(resultSet.getString("name"));
            staff.setEmail(resultSet.getString("email"));
            staff.setAddress(resultSet.getString("address"));
            staff.setSalary(resultSet.getDouble("salary"));
            staff.setDepartmentId(resultSet.getInt("department_id"));
            staffList.add(staff);
        }
        resultSet.close();
        disconnect();
        return staffList;
    }
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> staffList = new ArrayList<>();

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM staff");

        while (resultSet.next()) {
            Staff staff = new Staff();
            staff.setId(resultSet.getInt("id"));
            staff.setName(resultSet.getString("name"));
            staff.setEmail(resultSet.getString("email"));
            staff.setAddress(resultSet.getString("address"));
            staff.setSalary(resultSet.getDouble("salary"));
            staff.setDepartmentId(resultSet.getInt("department_id"));
            staffList.add(staff);
        }
        resultSet.close();
        statement.close();

        disconnect();

        return staffList;
    }
    public Staff getStaffById(int id) throws SQLException {
        Staff staff = null;
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement("SELECT * FROM staff WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            staff = new Staff();
            staff.setId(resultSet.getInt("id"));
            staff.setName(resultSet.getString("name"));
            staff.setEmail(resultSet.getString("email"));
            staff.setAddress(resultSet.getString("address"));
            staff.setSalary(resultSet.getDouble("salary"));
            staff.setDepartmentId(resultSet.getInt("department_id"));
        }
        resultSet.close();
        statement.close();

        disconnect();

        return staff;
    }

    public void addStaff(Staff staff) throws SQLException {
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement("INSERT INTO staff (name, email, address, salary, department_id) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, staff.getName());
        statement.setString(2, staff.getEmail());
        statement.setString(3, staff.getAddress());
        statement.setDouble(4, staff.getSalary());
        statement.setInt(5, staff.getDepartmentId());
        statement.executeUpdate();
        statement.close();
        disconnect();
    }
    public void updateStaff(Staff staff) throws SQLException {
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement("UPDATE staff SET name = ?, email = ?, address = ?, salary = ?, department_id = ? WHERE id = ?");
        statement.setString(1, staff.getName());
        statement.setString(2, staff.getEmail());
        statement.setString(3, staff.getAddress());
        statement.setDouble(4, staff.getSalary());
        statement.setInt(5, staff.getDepartmentId());
        statement.setInt(6, staff.getId());
        statement.executeUpdate();
        statement.close();
        disconnect();
    }

    public void deleteStaff(int id) throws SQLException {
        connect();
        PreparedStatement statement = jdbcConnection.prepareStatement("DELETE FROM staff WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
//        disconnect();
//        connect();
        PreparedStatement resetStatement = jdbcConnection.prepareStatement("ALTER TABLE staff AUTO_INCREMENT = ?");
        resetStatement.setInt(1, id);
        resetStatement.executeUpdate();
        resetStatement.close();
        disconnect();
    }
}