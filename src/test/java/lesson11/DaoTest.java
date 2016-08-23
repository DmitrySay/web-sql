package lesson11;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import dao.GroupDao;
import domain.Group;
import sql.SqlGroupDao;

public class DaoTest {

	@Test
	public void selectAllGroupsTest() throws Exception {

		GroupDao groupDao = new SqlGroupDao();
		List<Group> list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		groupDao.close();

	}

	@Test
	public void selectGroupTest() throws Exception {

		GroupDao groupDao = new SqlGroupDao();

		List<Group> list;
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		Group g = list.get(0);
		Group group = groupDao.selectGroup(g.getId());

		Assert.assertNotNull(group);
		
		groupDao.close();

	}

	@Test
	public void updateGroupTest() throws Exception {
		GroupDao groupDao = new SqlGroupDao();
		groupDao.insertGroup(3000, "Создание");

		List<Group> list;
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		Group g = list.get(list.size() - 1);
		int id = g.getId();
		int number = g.getNumber();
		String department = g.getDepartment();

		groupDao.updateGroup(id, number+1, department+"new");
	
		
		List<Group> listNew;
		listNew = groupDao.selectAllGroups();
		Assert.assertNotNull(listNew);
		Assert.assertTrue(listNew.size() > 0);
		Group gNew = listNew.get(list.size() - 1);
		int idNew = gNew.getId();
		int numberNew = gNew.getNumber();
		String departmentNew = gNew.getDepartment();
		
		
		Assert.assertEquals(id, idNew);
		Assert.assertEquals(number+1, numberNew);
		Assert.assertEquals(department+"new", departmentNew);
		
		groupDao.close();
	}

	@Test
	public void insertGroupTest() throws Exception {
		GroupDao groupDao = new SqlGroupDao();
		List<Group> list;
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		int oldSize = list.size();
		groupDao.insertGroup(3000, "Создание");
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		int newSize = list.size();

		Assert.assertEquals(newSize, oldSize + 1);
	
		groupDao.close();
	}

	@Test
	public void deleteGroupTest() throws Exception {
		GroupDao groupDao = new SqlGroupDao();
		groupDao.insertGroup(2000, "Удаление");
		List<Group> list;
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > 0);
		int oldSize = list.size();
		Group g = list.get(list.size() - 1);
		groupDao.deleteGroup(g.getId());
		list = groupDao.selectAllGroups();
		Assert.assertNotNull(list);
		int newSize = list.size();
	
		Assert.assertEquals(newSize, oldSize - 1);

		groupDao.close();
	}

}