package ua.nure.kn.mishchenko.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.mishchenko.usermanagement.User;


public class AddServletTest extends MockServletTestCase {

	public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }

    public void testAdd() {
        Date date = new Date();
        User newUser = new User("Anastasiia", "Mishchenko", date);
        User user = new User(new Long(1000), "Anastasiia", "Mishchenko", date);
        getMockUserDao().expectAndReturn("create", newUser, user);
        
        addRequestParameter("firstName", "Anastasiia");
        addRequestParameter("lastName", "Mishchenko");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
    
   
    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Mishchenko");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        assertSame("First name is empty", errorMessage);        
    }

    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "Anastasiia");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        assertSame("Last name is empty", errorMessage);
    }

    public void testAddEmptyDate() {
        addRequestParameter("firstName", "Anastasiia");
        addRequestParameter("lastName", "Mishchenko");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        assertSame("Date is empty", errorMessage);
    }

    public void testAddEmptyDateIncorrect() {
        addRequestParameter("firstName", "Anastasiia");
        addRequestParameter("lastName", "Mishchenko");
        addRequestParameter("date", "doppopppp");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
        assertSame("Date format is incorrect", errorMessage);
}


}
