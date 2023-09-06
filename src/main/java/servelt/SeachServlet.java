    package servelt;

    import dao.StaffDAO;
    import staff.model.Staff;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;

    @WebServlet("/seach")
    public class SeachServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String keyword = request.getParameter("search");
            StaffDAO staffDAO = new StaffDAO();
            if (keyword != null && !keyword.isEmpty()) {
                try {
                    List<Staff> staffList = staffDAO.getStaffByName(keyword);
                    request.setAttribute("staffList", staffList);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<Staff> staffList = staffDAO.getAllStaff();
                    request.setAttribute("staffList", staffList);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            request.getRequestDispatcher("seach.jsp").forward(request, response);
        }
    }

