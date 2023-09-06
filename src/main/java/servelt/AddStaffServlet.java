package servelt;
import dao.StaffDAO;
import staff.model.Staff;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/addStaff")
public class AddStaffServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
    private StaffDAO staffDAO;
    public void init() {
        staffDAO = new StaffDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addStaff.jsp");
        requestDispatcher.forward(req, resp);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String salarystr = request.getParameter("salary");
        String departmentIdstr = request.getParameter("departmentId");
        if (name==""||
            email==""||
            address==""||
            salarystr==""||
            departmentIdstr==""){
            response.sendRedirect("/addStaff");
            return;
        }
        int salary = Integer.parseInt(salarystr);
        int departmentId =Integer.parseInt(departmentIdstr);
        Staff staff = new Staff(name, email, address, salary, departmentId);
        try {
            staffDAO.addStaff(staff);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/staff");
    }
}
