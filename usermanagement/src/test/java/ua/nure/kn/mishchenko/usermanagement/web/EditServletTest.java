package ua.nure.kn.mishchenko.usermanagement.web;

import java.util.Date;
import java.text.DateFormat;

import ua.nure.kn.mishchenko.usermanagement.User;

public class EditServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}
	
	public void testEdit() {
		Date date = new Date();
		User user = new User(new Long(1000), "Anastasiia", "Mishchenko", date);
		getMockUserDao().expectAndReturn("update", user);
		
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Anastasiia");
		addRequestParameter("lastName", "Mishchenko");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "ok");
		doPost();
	}
	
    public void testEditEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("id", "1000");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        assertSame("First name is empty", errorMessage);
}
	
	public void testEditEmptyLastName() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "Anastasiia");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);	
	}
	
	public void testEditEmptyDate() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("lastName", "Mishchenko");
		addRequestParameter("okButton", "ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);	
	}
	
	public void testEditEmptyDateIncorrect() {
		Date date = new Date();
		addRequestParameter("id", "1000");
		addRequestParameter("lastName", "Mishchenko");
		addRequestParameter("date", "ihateprogramming");
		addRequestParameter("okButton", "ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockSession().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);	
	}
	
	

	

}
