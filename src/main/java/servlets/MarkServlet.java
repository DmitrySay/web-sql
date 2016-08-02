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


import dao.MarkDao;
import domain.Mark;



@WebServlet("/MarkServlet")
public class MarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession();
			MarkDao markDao = (MarkDao) session.getAttribute("markDao");
			
			PrintWriter out = response.getWriter();

			try {
				List<Mark> mark = markDao.selectAllMarks();
				session.setAttribute("messageMark", mark);
				RequestDispatcher rd = request.getRequestDispatcher("/MarksShow.jsp");
				rd.forward(request, response);
				
				
			} catch (Exception e) {

				out.println("Ошибка выполнения selectAllMarks");
			}

			out.close();

		} catch (Exception e) {
			System.out.println("Ошибка выполнения doGet MarkServlet");

		}	
		
		
		
		
		
	}

}
