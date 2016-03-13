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

  String    username = null;
  String    password = null;
  String    firstName = null;
  String    lastName = null;
  int       ID = 0;
    
    //create a new Ledger Object
    Ledger ledger = new Ledger();
    
    //create an ArrayList full of strings
ArrayList<String> params = new ArrayList();

ArrayList<User> results = ledger.search(params, params);

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(String username) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
  
    //Login()function to authenticate credentials so user can proceed to
    //main menu
    //Authenticate function allows user 3 tries to login
    
    public void Login(String name, String password)
    {   int x = 1;
        //allow user 3 tries to login
        for (int i = 0; i < 3; i++)
        {
            while(x != 0)
                x = password.compareTo(getPassword(name));
                
            if(x == 0) {
                    System.out.println("Login Successful");
                     /*code to return to main menu is placed here
            
            
                    */
                }
                    else
                    System.out.println("User name and password did not match. Try again. Error Code: " + x);
                    //User is taken back to Login() to reenter user name and password
               
            /* enter code here to return to main menu
                    
                    */
                    
        }//close for loop   
    
        //code to close program?
       
    }//close Authenticate
              
    //store registration information
    public void Register () {
        
    //call information method to get and store information
    
    Information();
		
    //while the user does not choose option 1 to register again or two to 
    //return to the main menu the request loops
		
    EditInformation();
    
        
    } 
   
    
        
    public void Information (){
		
		//What is your first name?
                Scanner first = new Scanner(System.in);
		System.out.print("Please, register by entering your first name.");
		String f = first.nextLine();
		setFirstName(f);
		
		
		//What is your last name?
                Scanner last = new Scanner(System.in);
		System.out.print("Please, enter your last name.");
		String l = last.nextLine();
		setLastName(l);
		
		//Create a user name
                Scanner user = new Scanner(System.in);
		System.out.print("Please, create a User Name to access your account with.");
		String u = user.nextLine();
		setUsername(u);
		
		//Create a unique password
                Scanner pword = new Scanner(System.in);
		System.out.print("Please, create a unique password for your account.");
		String p = pword.nextLine();
		setPassword(p);
		
		//Display output
		
		System.out.println("Your first and last name are: " + f + " " + l );
		System.out.println("You entered your User Name as: " + " " + u );
		System.out.println("You entered your password as: " + " " + p );
		System.out.printf("\n");
				
	}
		
    public void EditInformation(){
	int choice = 0;
	boolean done = false;
		
	while (done = false)
	{
	//Enter 1 if information is incorrect
	//and go through registration again
	System.out.println("Enter 1 to change your information.");

	//Enter 2 to return to the Main Menu
	System.out.println("Enter 2 to return to the Main Menu");
	
	//Scanner to receive screen input
	Scanner c = new Scanner(System.in);
		
	//int  holds user's choice 
        choice = c.nextInt();
		
	//Confirm one or two was selected		
	if (choice == 1 ) {
                    
            Register();//confirm funtion goes to a switch which allows to user to register again
                                       
        }//end if
		
        if (choice == 2) {
            done = true;//change boolean to stop while loop
             
            //thank you message
            System.out.println("Thank you for Creating an Account");
                   
            /*code to return to main menu is placed here
            
            
            */
 
        }//end if
                
        else {
            //user entered something other than 1 or 2
                    
            System.out.println("You entered something other than 1 or 2.  Try again.");
            done = false;	//boolean remains false the while loop continues until 2 is entered.
        }//end else
        
        }//end while loop
	
    }//end EditInformation() 
				
    }//end Class User
                       
    


