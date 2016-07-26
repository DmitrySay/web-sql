package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.StudentDao;
import sql.SqlStudentDao;


@WebServlet("/StudentCreateUpdateDeleteServlet")
public class StudentCreateUpdateDeleteServlet extends HttpServlet {
	
	private int id;
	private String name;
	private String surname;
	private int groupId;
	private String action;
	private static final long serialVersionUID = 1L;
    
	
	
	
	public StudentCreateUpdateDeleteServlet() {
        super();
       
        
		
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
		request.setCharacterEncoding("UTF-8");
		action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		StudentDao studentDao= (StudentDao) session.getAttribute("studentDao");

		
		if (action.equalsIgnoreCase("insert"))   {

			id = Integer.valueOf(request.getParameter("id"));
			name = request.getParameter("name");
			surname = request.getParameter("surname");
			groupId = Integer.valueOf(request.getParameter("groupId"));
		
		try {
	
		//	StudentDao studentDao = new SqlStudentDao();
			studentDao.insertStudent(id, name, surname, groupId);
		//	studentDao.close();
			response.sendRedirect("/lesson11/students/view");
			System.out.println("Record create Successfully");

		} catch (Exception e) {
			System.out.println("Ошибка выполнения webCreateStudent");
		}
	
		} else if (action.equalsIgnoreCase("update")) {
			
			id = Integer.valueOf(request.getParameter("id"));
			name = request.getParameter("name");
			surname = request.getParameter("surname");
			groupId = Integer.valueOf(request.getParameter("groupId"));

			
		            
			try {
			
				//StudentDao studentDao = new SqlStudentDao();
				studentDao.updateStudent(id, name, surname, groupId);
				//studentDao.close();
				response.sendRedirect("/lesson11/students/view");
				 System.out.println("Record updated Successfully");

			} catch (Exception e) {
				System.out.println("Ошибка выполнения webUpdateStudent");
			}
		} else if (action.equalsIgnoreCase("delete")) {
	
			id = Integer.valueOf(request.getParameter("id"));
			try {
		
			//	StudentDao studentDao = new SqlStudentDao();
				studentDao.deleteStudent(id);
			//	studentDao.close();
				response.sendRedirect("/lesson11/students/view");
				System.out.println("Record deleted Successfully");

			} catch (Exception e) {
				System.out.println("Ошибка выполнения webDeleteStudent");
			}
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
