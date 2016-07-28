package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DAOException;
import dao.StudentDao;
import domain.Student;
import java.util.List;

@WebServlet(name = "/StudentServlet", urlPatterns = "/StudentServlet")
public class StudentServlet extends HttpServlet {

	private int id;
	private String name;
	private String surname;
	private int groupId;
	private String action=null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");

			PrintWriter out = response.getWriter();

			try {
				List<Student> l = studentDao.selectAllStudents();
				session.setAttribute("message", l);
				response.sendRedirect("/lesson11/StudentsShow.jsp");
			} catch (DAOException e) {

				out.println("Ошибка выполнения selectAllStudents");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения doGet StudentServlet");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		action = request.getParameter("action");
		
		try {

			PrintWriter out = response.getWriter();
			String action = request.getParameter("action");

			HttpSession session = request.getSession();
			StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");
			
			
			if (action.equalsIgnoreCase("insert")) {

				id = Integer.valueOf(request.getParameter("id"));
				name = request.getParameter("name");
				surname = request.getParameter("surname");
				groupId = Integer.valueOf(request.getParameter("groupId"));

				try {

					studentDao.insertStudent(id, name, surname, groupId);

				} catch (Exception e) {
					out.println("Ошибка выполнения CreateStudent");
				}

			} else if (action.equalsIgnoreCase("update")) {

				id = Integer.valueOf(request.getParameter("id"));
				name = request.getParameter("name");
				surname = request.getParameter("surname");
				groupId = Integer.valueOf(request.getParameter("groupId"));

				try {

					studentDao.updateStudent(id, name, surname, groupId);

				} catch (Exception e) {
					out.println("Ошибка выполнения UpdateStudent");
				}
			} else if (action.equalsIgnoreCase("delete")) {

				id = Integer.valueOf(request.getParameter("id"));

				try {

					studentDao.deleteStudent(id);

				} catch (Exception e) {
					out.println("Ошибка выполнения DeleteStudent");
				}
			} else {
				out.println("Не задан action ");
			}

			response.sendRedirect("/lesson11/StudentServlet");

		} catch (Exception e) {
			System.out.println("Ошибка выполнения doPost StudentServlet");

		}

	}
}
