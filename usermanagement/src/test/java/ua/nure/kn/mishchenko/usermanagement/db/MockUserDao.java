package ua.nure.kn.mishchenko.usermanagement.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//import main.java.ua.nure.kn.mishchenko.usermanagement.db.UserDao;
import ua.nure.kn.mishchenko.usermanagement.User;

public class MockUserDao implements UserDao {

	private long id = 0;
	private Map users = new HashMap();
	
	
	public User create(User user) throws DatabaseException {
		Long currentId = new Long(++id);
		user.setId(currentId);
		users.put(currentId, user);
		return user;
	}

	@Override
	public void update(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);

	}

	@Override
	public void delete(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);

	}

	@Override
	public User find(Long id) throws DatabaseException {
		return (User) users.get(id);
	}

	@Override
	public Collection findAll() throws DatabaseException {
		return users.values();
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection find(String firstName, String lastName) throws DatabaseException {
		 throw new UnsupportedOperationException();
	}

}
