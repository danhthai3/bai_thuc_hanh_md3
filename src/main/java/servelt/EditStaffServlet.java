package servelt;

import dao.StaffDAO;
import staff.model.Staff;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editStaff")
public class EditStaffServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;

    private StaffDAO staffDAO;

    public void init() {
        staffDAO = new StaffDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = null;
        try {
            staff = staffDAO.getStaffById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("editStaff.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        Staff staff = new Staff(id, name, email, address, salary, departmentId);
        try {
            staffDAO.updateStaff(staff);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("staff");
    }
}