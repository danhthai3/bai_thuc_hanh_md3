package servelt;

import dao.StaffDAO;
import staff.model.Staff;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StaffDAO staffDAO;

    public void init() {
        staffDAO = new StaffDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Staff> staffList = null;
        try {
            staffList = staffDAO.getAllStaff();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        request.setAttribute("staffList", staffList);
        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }
}