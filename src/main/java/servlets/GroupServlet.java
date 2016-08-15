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

import dao.GroupDao;
import domain.Group;

@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void selectAllGroups(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			GroupDao groupDao = (GroupDao) session.getAttribute("groupDao");
			PrintWriter out = response.getWriter();

			try {
				List<Group> group = groupDao.selectAllGroups();
				session.setAttribute("messageGroup", group);

				RequestDispatcher rd = request.getRequestDispatcher("/GroupsShow.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				out.println("Ошибка выполнения selectAllGroups");
			}
			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения метода selectAllGroups");
		}
	}

	public void deleteGroup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			GroupDao groupDao = (GroupDao) session.getAttribute("groupDao");
			PrintWriter out = response.getWriter();

			int id = Integer.parseInt(request.getParameter("idGroup"));

			try {
				groupDao.deleteGroup(id);

				List<Group> group = groupDao.selectAllGroups();
				session.setAttribute("messageGroup", group);

				RequestDispatcher rd = request.getRequestDispatcher("/GroupsShow.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				out.println("Ошибка выполнения deleteGroup");
			}
			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения метода deleteGroup");
		}

	}

	public void selectGroup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			GroupDao groupDao = (GroupDao) session.getAttribute("groupDao");
			PrintWriter out = response.getWriter();

			int id = Integer.parseInt(request.getParameter("idGroup"));

			try {

				Group g = groupDao.selectGroup(id);
				session.setAttribute("group", g);

				RequestDispatcher rd = request.getRequestDispatcher("/Group.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				out.println("Ошибка выполнения selectGroup");
			}
			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения метода selectGroup");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		if (action.equalsIgnoreCase("listGroup")) {

			selectAllGroups(request, response);

		} else if (action.equalsIgnoreCase("deleteGroup")) {

			deleteGroup(request, response);

		} else if (action.equalsIgnoreCase("updateFormGroup")) {

			selectGroup(request, response);

		} else if (action.equalsIgnoreCase("insertGroup")) {

			RequestDispatcher rd = request.getRequestDispatcher("/GroupInsert.jsp");
			rd.forward(request, response);

		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);

		}

		out.close();

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {

			HttpSession session = request.getSession();
			GroupDao groupDao = (GroupDao) session.getAttribute("groupDao");
			PrintWriter out = response.getWriter();

			String flag = "true";

			String stringid = request.getParameter("idGroup");
			String stringnumber = request.getParameter("number");
			String department = request.getParameter("department");

			if ((stringnumber.isEmpty()) || department.isEmpty()) {
				flag = "false";
			}

			if ((stringid == null || stringid.trim().isEmpty()) & flag.equals("true")) {

				try {

					int number = Integer.parseInt(request.getParameter("number"));
					groupDao.insertGroup(number, department);

					List<Group> group = groupDao.selectAllGroups();
					session.setAttribute("messageGroup", group);
					RequestDispatcher rd = request.getRequestDispatcher("/GroupsShow.jsp");
					rd.forward(request, response);

				} catch (Exception e) {

					out.println("Ошибка выполнения insertGroup");
				}

			} else if (flag.equals("true")) {

				try {
					int id = Integer.parseInt(request.getParameter("idGroup"));
					int number = Integer.parseInt(request.getParameter("number"));

					groupDao.updateGroup(id, number, department);

					Group g = groupDao.selectGroup(id);
					session.setAttribute("group", g);

					RequestDispatcher rd = request.getRequestDispatcher("/Group.jsp");
					rd.forward(request, response);

				} catch (Exception e) {

					out.println("Ошибка выполнения updateGroup");
				}
			} else if (flag.equals("false")) {
				out.println("Заполните, пожалуйста, все поля. <br>");
			}

			out.close();

		} catch (Exception e) {

			System.out.println("Ошибка выполнения метода doPost groupServlet ");
		}

	}
}