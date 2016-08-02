package servlets;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.DAOException;
import dao.GroupDao;
import dao.MarkDao;
import dao.StudentDao;
import sql.SqlGroupDao;
import sql.SqlMarkDao;
import sql.SqlStudentDao;

@WebListener
public class Listener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {

		try {
			StudentDao studentDao = new SqlStudentDao();
		event.getSession().setAttribute("studentDao", studentDao);
		} catch (DAOException e) {

			e.printStackTrace();
		}

		try {
			GroupDao groupDao = new SqlGroupDao();
			event.getSession().setAttribute("groupDao", groupDao);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		try {
			MarkDao markDao = new SqlMarkDao();
			event.getSession().setAttribute("markDao", markDao);
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}

	public void sessionDestroyed(HttpSessionEvent event) {

		GroupDao groupDao = (GroupDao) event.getSession().getAttribute("groupDao");

		try {
			groupDao.close();
		} catch (DAOException e) {

			e.printStackTrace();
		}

		MarkDao markDao = (MarkDao) event.getSession().getAttribute("markDao");

		try {
			markDao.close();
		} catch (DAOException e) {

			e.printStackTrace();
		}

		StudentDao studentDao = (StudentDao) event.getSession().getAttribute("studentDao");

		try {
			studentDao.close();
		} catch (DAOException e) {

			e.printStackTrace();
		}

	}
}
