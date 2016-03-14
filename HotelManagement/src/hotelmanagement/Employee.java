/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.*;
import java.lang.*;


/**
 *
 * @author Monique
 */
public class Employee extends User {
    
 //ArrayList<String> properties = new ArrayList();
 //Reservation ResObj = new Reservation();
 //Room RoomObj = new Room();
 
    Employee()
    {
        super();
    }

    Employee(String username, String password, String fName, String lName, int ID)
    {
        super(username, password, fName, lName, ID);
    }
    
    //EditReservation() is an overloaded function
    //to change StartDay pass two parameters (1, New Date)
    //and to change EndDay pass two paramenters (2, New Date)
    //to change room number pass in new room number,
    //to change total price paid pass in new total price,
    //to mark room as paid enter in true boolean
 
    public void editReservation(int choice, Date day ) 
    {   
        
           switch(choice)
      {
          case 1:
              //ResObj.setStartDate(day);
              break;
          case 2:
              //ResObj.setEndDate(day);
              break;
              default:
              /*
              code return to Main Menu here
              */
              
          
        }
        
    }
    
    public void editReservation(int room)
    {
        //ResObj.setRoomNumber(room);
        
    }
    
    public void editReservation(double price)     
    {
        //ResObj.setTotalPrice(price);
    }
    
    public void editReservation(boolean value)
    {
        //ResObj.setPaid(value);
    }
    
   //editRoom is an overloaded function 
   //To edit Room Type or Room Description
   //enter two parameters (1, Room Type)
   //or (2, Room Description)
   // An int parameter is passed to change room
   //number and a double parameter is passed to change rate
    
   
    public void editRoom(int Number) 
    {
        //RoomObj.setNumber(Number);
		
    }
	
    /**
     *
     * @param 1 to setType
     * @param 2 to setDescription
     */
    public void editRoom(int choice, String Info) 
    {
                switch(choice)
      {
          case 1:
              //RoomObj.setType(Info);
              break;
          case 2:
              //RoomObj.setDescription(Info);
              break;
              default:
              /*
              code return to Main Menu here
              */
              
          
        }
        	
    }
    
      public void editRoom(double Rate) 
    {
        //RoomObj.setRate(Rate);
		
    }
    
    
    public void getReport() 
    {
        //getReport from Archive
        
        //parse as you want
        
        //print to screen
		
		
    }
	
    public void cancel() 
    {
        /*
        code to return to HMS
        */
		
    }
    
}
