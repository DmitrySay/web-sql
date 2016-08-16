package sql;


import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlDaoFactory implements DaoFactory {

    private String user = "root";//Логин пользователя
    private String password = "root";//Пароль пользователя
    private String url = "jdbc:mysql://localhost:3306/daotalk?useSSL=false";//URL адрес
    private String driver = "com.mysql.jdbc.Driver";//Имя драйвера

    public Connection getConnection() throws DAOException {
        Connection connection;


        try {
            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            throw new DAOException("Не удалось создать соединение с БД", e);
        }
        return connection;
    }

    public SqlDaoFactory() throws DAOException {
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {

            throw new DAOException("Не удалось зарегистрирать драйвер", e);
        }
    }


    @Override
    public StudentDao getStudentDao(Connection connection) throws DAOException {

        return new SqlStudentDao();

    }


    @Override
    public GroupDao getGroupDao(Connection connection) throws DAOException {
        return new SqlGroupDao();
    }

    @Override
    public MarkDao getMarkDao(Connection connection) throws DAOException{
        return new SqlMarkDao();
    }
    
    @Override
    public UserDao getUserDao(Connection connection) throws DAOException{
        return new SqlUserDao();
    }


}



