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
    private Display   display;
    private Scanner   input;
    //create a reference to the Ledger Object
    Ledger LedgObj = Ledger.getLedger();
    HotelManagement hms = HotelManagement.getHMS();
    UserFactory userFac = UserFactory.getUserFactory();
    
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
    
  
    //Login()function to authenticate credentials so user can proceed to
    //main menu
    //Authenticate function allows user 3 tries to login
    //once login is successful, flag is set to 0 and loop exits
    
//    public void Login(ArrayList<User> userList, String name, String password)
//    {   
//        int x;
//        ArrayList<String> paramList = new ArrayList();
//        paramList.add(name);
//        ArrayList<User> returnList = LedgObj.search(userList, paramList); 
//        User returnUser = returnList.get(0);
//        
//        //allow user 3 tries to login
//        for (int i = 0; i < 3; i++)
//        {
//            x = password.compareTo(getPassword(name));
//            if(x == 0)
//            {
//                System.out.println("Login Successful");
//                if(returnUser.getClass().equals(hotelmanagement.Employee.class))
//                {
//                    display.setState(StateEnum.EMPLOYEE);
//                    display.update();
//                }
//                else if(returnUser.getClass().equals(hotelmanagement.Customer.class))
//                {
//                    display.setState(StateEnum.CUSTOMER);
//                    display.update();
//                }
//                
//            }
//            else
//            System.out.println("User name and password did not match. Try again. Error Code: " + x);
//        }//close for loop   
//        display.setState(StateEnum.MAIN);
//        display.update();
//        //code to close program?
//       
//    }//close Authenticate
//              
//    //store registration information
//    public void Register () {
//        
//    //call information method to get and store information
//    
//    Information();
//		
//    //while the user does not choose option 1 to register again or two to 
//    //return to the main menu the request loops
//		
//    EditInformation();
//    
//        
////    } 
//   
//    
//        
//    public void Information (){
//		
//        //What is your first name?
//        System.out.println("Please enter your first name: ");
//        String f = input.nextLine();
//        setFirstName(f);
//
//        //What is your last name?
//        System.out.println("Please enter your last name: ");
//        String l = input.nextLine();
//        setLastName(l);
//
//        //Create a user name
//        System.out.println("Please create a User Name to access your account with: ");
//        String u = input.nextLine();
//        setUsername(u);
//
//        //Create a unique password
//        System.out.println("Please create a unique password for your account: ");
//        String p = input.nextLine();
//        setPassword(p);
//
//        //Display output
//
//        System.out.println("Your first and last name are: " + f + " " + l );
//        System.out.println("You entered your User Name as: " + " " + u );
//        System.out.println("You entered your password as: " + " " + p );
//        
//    }
//		
//    public void EditInformation(){
//	int choice = 0;
//	boolean done = false;
//		
//	while (done = false)
//	{
//            //Enter 1 if information is incorrect
//            //and go through registration again
//            System.out.println("Enter 1 to change your information.");
//
//            //Enter 2 to return to the Main Menu
//            System.out.println("Enter 2 to return to the Main Menu");
//
//            //int  holds user's choice 
//            choice = input.nextInt();
//
//            //Confirm one or two was selected		
//            if (choice == 1 ) {
//
//                Register();//confirm funtion goes to a switch which allows to user to register again
//
//            }//end if
//
//            if (choice == 2) {
//                done = true;//change boolean to stop while loop
//
//                //thank you message
//                System.out.println("Thank you for Creating an Account");
//                
//                // add new user to HMS arraylist
//                userFac.createUser(this.getClass(), this.username, this.password, this.username, this.lastName, this.ID);
//                hms.allUsers.add(this);
//                
//                //return to main menu
//                display.setState(StateEnum.MAIN);
//                display.update();
//            }//end if
//
//            else {
//                //user entered something other than 1 or 2
//
//                System.out.println("You entered something other than 1 or 2.  Try again.");
//                done = false;	//boolean remains false the while loop continues until 2 is entered.
//            }//end else
//
//        }//end while loop
//	
//    }//end EditInformation() 
//	
//    
//      
//   
//
/*
    This is the new version of Register() was not saved on Sunday.
    This is the updated code given Tim's work on Reservation
    //Register() stores information passed to it by calling CreateUser() Method.
	*/
/*
    public void Register(Class<?> classArg, String username, String password, String fName, String lName, int ID)
    {
        //Calls method from Reservation to create registered user
        return createUser(classArg, username, password, fName,lName, ID);
    
    }
*/
    
    
    
    /* 
    Reservation Method I made was not saved apparently.  But that
    is okay because it due to changes that happened in Reservation later.
    Below is the Reservation Method that can be used by Employee
    or Customer */
   /*
    public static Reservation(Reservation(Date sDate, Date eDate, Room room, boolean payment, User user)
    {
        return Reservation(sDate, eDate, room, payment, user);
    
    }
    
  */
    
    
}//end Class User
                       
    


