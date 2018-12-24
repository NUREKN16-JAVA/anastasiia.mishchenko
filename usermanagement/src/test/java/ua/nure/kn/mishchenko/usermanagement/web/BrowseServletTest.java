package ua.nure.kn.mishchenko.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ua.nure.kn.mishchenko.usermanagement.User;

public class BrowseServletTest extends MockServletTestCase {

	
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}
	
	public void testBrowse() {
		User user = new User(new Long(1000), "Anastasiia", "Mishchenko", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection collection = (Collection) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of users in session ", collection);
		assertSame(list, collection);
	}
	
	public void testEdit() {
		User user = new User(new Long(1000), "Anastasiia", "Mishchenko", new Date());
		getMockUserDao().expectAndReturn("find", new Long(1000), user);
		addRequestParameter("editButon", "Edit");
		addRequestParameter("id", "1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find user in session", user);
		assertSame(user, userInSession);
		
	}
	
    public void testEditWhenUserIsNotSelected() {
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
    
    public void testDelete() {
        User user = new User(1000L, "Anastasiia", "Mishchenko", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        getMockUserDao().expect("delete", user);
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("message"));
    }
    
    
    public void testDeleteWhenUserIsNotSelected() {
        addRequestParameter("deleteButton", "Delete");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

    public void testDetails() {
        User user = new User(1000L, "Anastasiia", "Mischenko", new Date());
        getMockUserDao().expectAndReturn("find", 1000L, user);
        addRequestParameter("detailsButton", "Details");
        addRequestParameter("id", "1000");
        doPost();
        User userInSession = (User) getWebMockObjectFactory().
                                    getMockSession().
                                    getAttribute("user");
        assertNotNull(userInSession);
        assertSame(user, userInSession);
    }
    
     public void testDetailsWhenUserIsNotSelected() {
        addRequestParameter("detailsButton", "Details");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
}
	

