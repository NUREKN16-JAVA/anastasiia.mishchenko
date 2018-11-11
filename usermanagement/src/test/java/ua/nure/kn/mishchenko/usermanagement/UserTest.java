package ua.nure.kn.mishchenko.usermanagement;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


class UserTest {
	
    public static final long ID = 1L;
	public static final String FIRST_NAME = "Anastasiia";
	public static final String LAST_NAME  = "Mishchenko";
	public static final int    BIRTH_YEAR = 1999;

	private User user;
	
	public static int currentDay;

	@Before
	void setUp() throws Exception {
		
		user = new User(ID, FIRST_NAME, LAST_NAME, new SimpleDateFormat("d-MMM-yyyy").parse("13-Jun-1999"));
	}
	

	@Test
	  public void testGetFullName() {
		
        User user = new User(ID, FIRST_NAME, LAST_NAME, null);
        assertEquals("Anastasiia, Mishchenko", user.getFullName());
    }
	
	//Written in November 2018
	//In case the b-day was earlier this year
    @Test
    public void testGetAgeAfter() {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.set(BIRTH_YEAR, Calendar.JUNE, 13);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }
    
    //In case b-day is coming later this year
    @Test
    public void simpleAgeTestBefore() {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.DECEMBER, 13);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }
    
   //In case b-day was earlier this month
    @Test
    public void ageTestSameMonthAfter() {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.NOVEMBER, 3);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }

    //In case b-day is coming later this month
    @Test
    public void ageTestSameMonthBefore() {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.NOVEMBER, 29);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(18, user.getAge());
    }
    
    //In case b-day is today
    @Test
    public void ageTestSameDay() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());
        currentDay = currentDate.get(Calendar.DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, Calendar.NOVEMBER, currentDay);
        User user = new User(ID, FIRST_NAME, LAST_NAME, calendar.getTime());
        assertEquals(19, user.getAge());
    }
}
    


