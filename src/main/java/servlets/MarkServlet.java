package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.MarkDao;
import domain.Mark;

public class MarkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void selectAllMarks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			PrintWriter out = response.getWriter();

			try {
				List<Mark> listmark = markDao.selectAllMarks();
				session.setAttribute("listMark", listmark);
				RequestDispatcher rd = request.getRequestDispatcher("/MarksShow.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				out.println("Ошибка выполнения selectAllMarks");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения selectAllMarks");
		}

	}

	public void deleteMark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			PrintWriter out = response.getWriter();

			int id = Integer.parseInt(request.getParameter("idMark"));

			try {
				markDao.deleteMark(id);

				List<Mark> listmark = markDao.selectAllMarks();
				session.setAttribute("listMark", listmark);
				RequestDispatcher rd = request.getRequestDispatcher("/MarksShow.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				out.println("Ошибка выполнения SelectMark");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения selectAllMarks");
		}
	}

	public void selectMark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			PrintWriter out = response.getWriter();

			int id = Integer.parseInt(request.getParameter("idMark"));

			try {
				Mark mark = markDao.selectMark(id);

				session.setAttribute("mark", mark);
				RequestDispatcher rd = request.getRequestDispatcher("/Mark.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				out.println("Ошибка выполнения SelectMark");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения selectMark");
		}

	}

	public void insertMark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			PrintWriter out = response.getWriter();

			try {

				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int mark = Integer.parseInt(request.getParameter("mark"));

				markDao.insertMark(studentId, mark);

				List<Mark> listmark = markDao.selectAllMarks();
				session.setAttribute("listMark", listmark);
				RequestDispatcher rd = request.getRequestDispatcher("/MarksShow.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				out.println("Ошибка выполнения insertMark");
			}

			out.close();
		} catch (Exception e) {

			System.out.println("Ошибка выполнения метода insertMark в MarkServlet ");
		}

	}

	public void updateMark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			PrintWriter out = response.getWriter();

			try {
				int mark = Integer.parseInt(request.getParameter("mark"));
				int studentId = Integer.parseInt(request.getParameter("studentId"));
				int id = Integer.parseInt(request.getParameter("idMark"));

				markDao.updateMark(id, studentId, mark);

				Mark m = markDao.selectMark(id);
				session.setAttribute("mark", m);
				RequestDispatcher rd = request.getRequestDispatcher("/Mark.jsp");
				rd.forward(request, response);

			} catch (Exception e) {

				out.println("Ошибка выполнения updateMark");
			}

			out.close();

		} catch (Exception e) {

			System.out.println("Ошибка выполнения метода updateMark в MarkServlet ");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("listMark")) {

			selectAllMarks(request, response);

		} else if (action.equalsIgnoreCase("deleteMark")) {

			deleteMark(request, response);

		} else if (action.equalsIgnoreCase("updateFormMark")) {

			selectMark(request, response);

		} else if (action.equalsIgnoreCase("insertMark")) {

			RequestDispatcher rd = request.getRequestDispatcher("/MarkInsert.jsp");
			rd.forward(request, response);

		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		String stringmark = request.getParameter("mark");
		String stringstudentId = request.getParameter("studentId");
		String stringidMark = request.getParameter("idMark");
		String flag = "true";

		if ((stringmark.isEmpty()) || stringstudentId.isEmpty()) {
			flag = "false";
		}

		if ((stringidMark == null || stringidMark.trim().isEmpty())&flag.equals("true")) {

			insertMark(request, response);

		} else if (flag.equals("true")) {

			updateMark(request, response);

		} else if (flag.equals("false")) {

			out.println("Заполните, пожалуйста, все поля. <br>");
		}
		out.close();
	}

}
