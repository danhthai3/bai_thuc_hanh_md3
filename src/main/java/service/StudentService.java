package service;

import model.Classroom;
import model.Student;
import service.connect.ConnectMySQL;
import service.iService.IServiceCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IServiceCRUD<Student> {
    Connection connection = ConnectMySQL.getConnection();

    @Override
    public void add(Student student) {
        String sql = "insert into student(studentName, dateOfBirth, email, address, phoneNumber, classId) values (?,?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setObject(2, student.getDateOfBirth());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getClassId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(int id, Student student) {
        String sql = "update student set studentName=?, dateOfBirth=?, email=?, address=?, phoneNumber=?, classId=? where studentId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setObject(2, student.getDateOfBirth());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getClassId());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from student where studentId = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        String sql = "select student.*,classroom.className from student inner join classroom on student.classId=classroom.classId order by student.studentId;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String studentName = resultSet.getString("studentName");
                String dateOfBirthString = resultSet.getString("dateOfBirth");
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int classId = resultSet.getInt("classId");
                String className = resultSet.getString("className");

                Classroom classroom = new Classroom(classId, className);
                Student student = new Student(studentId, studentName, dateOfBirth, email, address, phoneNumber, classroom);

                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public Student findById(int id) {
//        Student student = null;
//        String sql = "select * from student where studentId = ?;";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int studentId = resultSet.getInt("studentId");
//                String studentName = resultSet.getString("studentName");
//                String dateOfBirth = resultSet.getString("dateOfBirth");
//                String email = resultSet.getString("email");
//                String address = resultSet.getString("address");
//                String phoneNumber = resultSet.getString("phoneNumber");
//                int classId = resultSet.getInt("classId");
//                String className = resultSet.getString("className");
//
//                Classroom classroom = new Classroom(classId, className);
//                student = new Student(studentId, studentName, dateOfBirth, email, address, phoneNumber, classroom);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return student;
        for (Student student : getAll()) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findByName(String name) {
        List<Student> studentList = new ArrayList<>();
        for (Student student : getAll()) {
            if (student.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                studentList.add(student);
            }
        }
        return studentList;
    }
}
