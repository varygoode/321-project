/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.*;



/**
 *
 * @author timothy
 */
public class User 
{
    private String    username;
    private String    password;
    private String    firstName;
    private String    lastName;
    private int       ID;
    
    public User() 
    {
        this.username = "guest";
        this.password = "12345";
        this.firstName = "Guest";
        this.lastName = "Guestington";
        this.ID = 9999;
    }

    public User(String username, String password, String firstName, String lastName, int ID) 
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
    }
    
    ////////////////////////////////////
    // Mutators & Accessors
    ////////////////////////////////////    
    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getLastName() 
    {
        return lastName;
    }

    public void setLastName(String lastName) 
    {
        this.lastName = lastName;
    }

    public int getID() 
    {
        return ID;
    }

    public void setID(int ID) 
    {
        this.ID = ID;
    }
}//end Class User
                       
    


