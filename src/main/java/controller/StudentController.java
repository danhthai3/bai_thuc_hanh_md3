package controller;

import model.Classroom;
import model.Student;
import service.ClassroomService;
import service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private StudentService studentService = new StudentService();
    private ClassroomService classroomService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "getAll":
                showFormGetAll(request, response);
                break;
            case "add":
                showFormAdd(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));

        studentService.delete(studentId);
        response.sendRedirect("student?action=getAll");
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentService.findById(studentId);
        request.setAttribute("student", student);

        List<Classroom> classroomList = classroomService.getAll();
        request.setAttribute("classroomList", classroomList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Classroom> classroomList = classroomService.getAll();
        request.setAttribute("classroomList", classroomList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormGetAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = studentService.getAll();
        request.setAttribute("studentList", studentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addStudent(request, response);
                break;
            case "edit":
                editStudent(request, response);
                break;
            case "search":
                searchStudent(request, response);
                break;
        }
    }

    private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Student> studentList = studentService.findByName(name);
        request.setAttribute("studentList", studentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/home.jsp");
        dispatcher.forward(request, response);
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String studentName = request.getParameter("studentName");
        String dateOfBirthString = request.getParameter("dateOfBirth");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classId = Integer.parseInt(request.getParameter("classId"));

        Classroom classroom = new Classroom(classId);
        Student student = new Student(studentId, studentName, dateOfBirth, email, address, phoneNumber, classroom);

        studentService.edit(studentId, student);

        response.sendRedirect("student?action=getAll");
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String studentName = request.getParameter("studentName");
        String dateOfBirthString = request.getParameter("dateOfBirth");
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classId = Integer.parseInt(request.getParameter("classId"));

        Classroom classroom = new Classroom(classId);
        Student student = new Student(studentName, dateOfBirth, email, address, phoneNumber, classroom);

        studentService.add(student);

        response.sendRedirect("student?action=getAll");
    }
}