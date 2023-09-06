package service;

import model.Classroom;
import service.connect.ConnectMySQL;
import service.iService.IServiceCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomService implements IServiceCRUD<Classroom> {
    Connection connection = ConnectMySQL.getConnection();

    @Override
    public void add(Classroom classroom) {

    }

    @Override
    public void edit(int id, Classroom classroom) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Classroom> getAll() {
        List<Classroom> classroomList = new ArrayList<>();
        String sql = "select * from classroom order by classroom.classId;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int classId = resultSet.getInt("classId");
                String className = resultSet.getString("className");
                Classroom classroom = new Classroom(classId, className);
                classroomList.add(classroom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classroomList;
    }
}
