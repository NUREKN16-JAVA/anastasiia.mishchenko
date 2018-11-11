package ua.nure.kn.mishchenko.usermanagement;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
	
    private Long id;
    private String firstName, lastName;
    private Date dateOfBirth;

    public User() {}

    public User(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

   
    public String getFullName(){
        return (new StringBuilder(getFirstName())).append(",").append(getLastName()).toString();
    }

    public int getAge() {
    	
        Calendar calendar = Calendar.getInstance();
        
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        
        calendar.setTime(getDateOfBirth());
        
        int birthYear = calendar.get(Calendar.YEAR);
        int birthMonth = calendar.get(Calendar.MONTH);
        int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
        
        int age = currentYear - birthYear;
        
        if(birthMonth > currentMonth | ((birthMonth == currentMonth) & (birthDay > currentDay))){
            --age;
        }

        return age;
    }
}