package servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.DAOException;
import dao.StudentDao;
import sql.SqlStudentDao;


@WebListener
public class MySessionListener implements HttpSessionListener {

	
    public void sessionCreated(HttpSessionEvent event)  { 
    	
    	
		
    	
    		  
			try {
				StudentDao studentDao = new SqlStudentDao();
				event.getSession().setAttribute("studentDao", studentDao);
			} catch (DAOException e) {
				
				e.printStackTrace();
			}
		      
    	

    }

	
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	StudentDao studentDao = (StudentDao) event.getSession().getAttribute("studentDao");
    	
    	try {
			studentDao.close();
		} catch (DAOException e) {
			
			e.printStackTrace();
		}
    }
	
}
