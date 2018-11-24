package ua.nure.kn.mishchenko.usermanagement;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
    
    // Was written in November 2018
    private static final int CURRENT_YEAR = 2018;
    private static final int YEAR_OF_BIRTH = 1999;
    
    // In case b-day was earlier this month
    private static final int ETALONE_AGE_1 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_1 = 5;
    private static final int MONTH_OF_BIRTH_1 = Calendar.DECEMBER;
    
    // In case b-day was earlier this year
    private static final int ETALONE_AGE_2 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_2 = 5;
    private static final int MONTH_OF_BIRTH_2 = Calendar.NOVEMBER;
    
    // In case b-day is today
    private static final int ETALONE_AGE_3 = CURRENT_YEAR - YEAR_OF_BIRTH;
    private static final int DAY_OF_BIRTH_3 = 11;
    private static final int MONTH_OF_BIRTH_3 = Calendar.DECEMBER;
    
    // In case b-day is coming later this month
    private static final int ETALONE_AGE_4 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_4 = 21;
    private static final int MONTH_OF_BIRTH_4 = Calendar.DECEMBER;
    
    // In case b-day is coming later this year
    private static final int ETALONE_AGE_5 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
    private static final int DAY_OF_BIRTH_5 = 11;
    private static final int MONTH_OF_BIRTH_5 = Calendar.DECEMBER;

    
    private User user;
    private Date dateOfBirthd;
    
    protected void setUp() throws Exception {
        super.setUp();
        user = new User();
    }
    
    public void testGetFullName() {
        user.setFirstName("Anastasiia");
        user.setLastName("Mishchenko");
        assertEquals("Anastasiia, Mishchenko", user.getFullName());
    }
    
    public void testGetAge1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
        dateOfBirthd = calendar.getTime();
        user.setDateOfBirth(dateOfBirthd);
        assertEquals(ETALONE_AGE_1, user.getAge());
    }
    
    public void testGetAge2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
        dateOfBirthd = calendar.getTime();
        user.setDateOfBirth(dateOfBirthd);
        assertEquals(ETALONE_AGE_2, user.getAge());
    }
}