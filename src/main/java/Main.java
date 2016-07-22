import dao.GroupDao;
import dao.MarkDao;
import dao.StudentDao;
import sql.SqlGroupDao;
import sql.SqlMarkDao;
import sql.SqlStudentDao;


public class Main {


    public static void main(String[] ars) {


        try {
            StudentDao studentDao = new SqlStudentDao();

            System.out.println(studentDao.selectStudent(5));
         //   System.out.println(studentDao.selectStudent(6));
         //   studentDao.selectAllStudentsGroupsMarks();
         //   studentDao.selectAllStudentAndGroup();
         //   studentDao.selectAllStudents();

            studentDao.close();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения studentDao");
        }

/*
        try {
            GroupDao groupDao = new SqlGroupDao();
            groupDao.selectAllGroups();

            groupDao.close();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения groupDao");
        }

        try {
            MarkDao markDao = new SqlMarkDao();
            markDao.selectAllMarks();

            markDao.close();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения markDao");
        }
*/
    }
}




