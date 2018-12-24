package ua.nure.kn.mishchenko.usermanagement.web;

public class DetailsServletTest extends MockServletTestCase {
	
    protected void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testOkButton() {
        addRequestParameter("okButton", "Ok");
        doPost();
        assertNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

}