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
import dao.StudentDao;
import domain.Student;
import java.util.List;

@WebServlet(name = "/StudentServlet", urlPatterns = "/StudentServlet")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String redirect = "";

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession();
			StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");
			PrintWriter out = response.getWriter();

			if (action.equalsIgnoreCase("listUser")) {

				try {
					List<Student> list = studentDao.selectAllStudents();
					session.setAttribute("listStudent", list);
					redirect = "/StudentsShow.jsp";

				} catch (Exception e) {

					out.println("Ошибка выполнения selectAllStudents");
				}

			} else if (action.equalsIgnoreCase("delete")) {

				int id = Integer.parseInt(request.getParameter("id"));

				try {

					studentDao.deleteStudent(id);
					List<Student> list = studentDao.selectAllStudents();
					session.setAttribute("listStudent", list);
					redirect = "/StudentsShow.jsp";

				} catch (Exception e) {
					out.println("Ошибка выполнения DeleteStudent");
				}

			} else if (action.equalsIgnoreCase("updateForm")) {

				int id = Integer.parseInt(request.getParameter("id"));

				try {
					Student student = studentDao.selectStudent(id);
					session.setAttribute("student", student);
					redirect = "/Student.jsp";

				} catch (Exception e) {

					out.println("Ошибка выполнения SelectStudent");
				}

			} else if (action.equalsIgnoreCase("insert")) {

				redirect = "/StudentInsert.jsp";

			} else {

				redirect = "/";

			}

			RequestDispatcher rd = request.getRequestDispatcher(redirect);
			rd.forward(request, response);

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения метода doGet StudentServlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		

		try {
			HttpSession session = request.getSession();
			StudentDao studentDao = (StudentDao) session.getAttribute("studentDao");
			PrintWriter out = response.getWriter();
			String stringid = request.getParameter("id");
		
		
			String flag=null;
			String stringgroupId =request.getParameter("groupId");
			
			if (stringgroupId.equals("1")||stringgroupId.equals("2")||stringgroupId.equals("3")){
				flag="true";
				
			} else {
				flag="false";
				}

			
			if ( (stringid == null || stringid.trim().isEmpty()) & flag.equals("true") ) {

				try {
					int groupId = Integer.parseInt(request.getParameter("groupId"));
					studentDao.insertStudent(name, surname, groupId);

					List<Student> list = studentDao.selectAllStudents();
					session.setAttribute("listStudent", list);
					RequestDispatcher rd = request.getRequestDispatcher("/StudentsShow.jsp");
					rd.forward(request, response);

				} catch (Exception e) {

					out.println("Ошибка выполнения insertStudent");
				}

			} else if (flag.equals("true")){

				try {
					int id = Integer.parseInt(request.getParameter("id"));
					int groupId = Integer.parseInt(request.getParameter("groupId"));
					studentDao.updateStudent(id, name, surname, groupId);

					Student student = studentDao.selectStudent(id);
					session.setAttribute("student", student);

					RequestDispatcher rd = request.getRequestDispatcher("/Student.jsp");
					rd.forward(request, response);

				} catch (Exception e) {

					out.println("Ошибка выполнения updateStudent");
				}
			} else if (flag.equals("false")){
				out.println("Ошибка заполнения поля groupId");
			}

			out.close();

		} catch (Exception e) {

			System.out.println("Ошибка выполнения метода doPost StudentServlet ");
		}

	}
}
