package ua.nure.kn.mishchenko.usermanagement.db;



import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import junit.framework.TestCase;

import ua.nure.kn.mishchenko.usermanagement.User;

public class HsqlUserDaoTest extends DatabaseTestCase{

    private static final Long TEST_FIND_DELETE_ID = 1000L;
    private static final String TEST_LASTNAME = "Mishchenko";
    private static final String TEST_NAME = "Anastasiia";
    
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Anastasiia");
			user.setLastName("Mishchenko");
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size",2, collection.size());
			
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testUpdateUser() throws DatabaseException {
	        User user = dao.find(TEST_FIND_DELETE_ID);
	        assertNotNull(user);
	        user.setFirstName("Alexander");
	        dao.update(user);
	        User updatedUser = dao.find(TEST_FIND_DELETE_ID);
	        assertEquals(user.getFirstName(), updatedUser.getFirstName());
	}
	
	public void testDelete() {
        User deletedUser = new User();
        deletedUser.setId(TEST_FIND_DELETE_ID);
        try {
            dao.delete(deletedUser);
            dao.find(TEST_FIND_DELETE_ID);
            fail();
        } catch (DatabaseException e) {
        	String left = e.getMessage();
        	String right = TEST_FIND_DELETE_ID.toString();
        	left = left.substring(left.length()-right.length());
        	assertTrue(left.equals(right));
        }
	}
	
    public void testFind() throws DatabaseException {
        User userToCheck = dao.find(TEST_FIND_DELETE_ID);
        assertNotNull(userToCheck);
        assertEquals(TEST_NAME, userToCheck.getFirstName());
        assertEquals(TEST_LASTNAME, userToCheck.getLastName());
}
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}


	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
