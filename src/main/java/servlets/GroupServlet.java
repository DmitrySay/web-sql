package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOException;
import dao.GroupDao;
import domain.Group;

@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession();
			GroupDao groupDao = (GroupDao) session.getAttribute("groupDao");

			PrintWriter out = response.getWriter();

			try {
				List<Group> group = groupDao.selectAllGroups();
				session.setAttribute("messageGroup", group);
				RequestDispatcher rd = request.getRequestDispatcher("/GroupsShow.jsp");
				rd.forward(request, response);

			} catch (DAOException e) {

				out.println("Ошибка выполнения selectAllGroups");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения doGet GroupServlet");

		}
	}

}
