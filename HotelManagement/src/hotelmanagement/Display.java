/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author andreapressnell
 */
public class Display 
{
    private Scanner input = new Scanner(System.in);
    private StateEnum state;
    int menuOption;
    
    //HotelManagement hms = HotelManagement.getHMS();
    
    public Display()
    {
        //constructor 
        state = StateEnum.MAIN;
    }
    
    private void startMenu()
    {            
            System.out.println("**MAIN MENU**");
            System.out.println("=============");
            System.out.println("1. Login"); //returning user
            System.out.println("2. Register"); //creates new employee or customer
            System.out.println("3. Search"); //defaults to customer Menu
            System.out.println("4. Quit"); //endProgram
            System.out.println("Please enter the digit to make a selection.");        
    }
    
    private void custMenu()
    {
            System.out.println("**CUSTOMER MENU**");
            System.out.println("=================");
            System.out.println("1. Search/Reserve"); //takes you to search menu
            System.out.println("2. Return to Main Menu");
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
    }
    
    private void empMenu()
    {
            System.out.println("**EMPLOYEE MENU**");
            System.out.println("=================");
            System.out.println("1. Search"); //more search options? 
//            System.out.println("2. Reserve"); //takes you to reservation menu
            System.out.println("2. Alter Room"); 
            System.out.println("3. Check-In"); 
            System.out.println("4. Check-Out");
            System.out.println("5. Report"); //for now prints 'database'
            System.out.println("6. Return to Main Menu"); //for now prints 'database'
            System.out.println("7. Cancel");
    }
    
    private void searchMenu()
    {
        //may need a separate search menu for employee and another for customer
            System.out.println("**SEARCH**");
            System.out.println("==========");
            System.out.println("1. Search for a Room"); //filters to room data will be applied 
            System.out.println("2. Make a Reservation"); //takes you to Reservation Menu
            System.out.println("3. Return to Main Menu");
            System.out.println("4. Cancel");
            System.out.println("Please enter the digit to make a selection.");
    }
    
//    private void reservationMenu()
//    {
//            System.out.println("**Reservation Menu**");
//            System.out.println("====================");
//            System.out.println("1. Reserve Room");
//            System.out.println("2. Return to Main Menu");
//            System.out.println("3. Cancel");
//            System.out.println("Please enter the digit to make a selection.");
//    }
    
    private void checkInMenu()
    {
            System.out.println("**Check-In Menu**");
            System.out.println("=================");
            System.out.println("1. Find Reservation");
            System.out.println("2. Return to Main Menu");
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
    }
    
    private void checkOutMenu()
    {
            System.out.println("**Check-Out Menu**");
            System.out.println("==================");
            System.out.println("1. Find a Reservation");
            System.out.println("2. Complete Check-Out");
            System.out.println("3. Return to Main Menu");
            System.out.println("4. Cancel");
            System.out.println("Please enter the digit to make a selection.");
    }
    
    private void cancelMenu()
    {
            System.out.println("**Cancel**");
            System.out.println("==========");
            System.out.println("Select an option from the list:");
            System.out.println("1. Return to Main Menu");
            System.out.println("2. Return to Search Menu");
            System.out.println("3. Return to Main Menu");
            System.out.println("4. Quit"); //go to quitMenu
            System.out.println("Please enter the digit to make a selection.");
    }
    
    private void quitMenu()
    {
            System.out.println("=============");
            System.out.println("**Good Bye!**");
            System.out.println("=============");
    }
    
    /**
     * Updates the display to show appropriate menu or prompt.
     */
    public void update()
    {
        switch(state)
        {
            case MAIN:
                startMenu();
                break;
            case CUSTOMER:
                custMenu();
                break;
            case EMPLOYEE:
                empMenu();
                break;
            case SEARCH:
                searchMenu();
                break;
//            case RESERVATION:
//                reservationMenu();
//                break;
            case CHECKIN:
                checkInMenu();
                break;
            case CHECKOUT:
                checkOutMenu();
                break;
            case CANCEL:
                cancelMenu();
                break;
            case QUIT:
                quitMenu();
                break;
            default:
                startMenu();
                break;
        }
    }
    
    public void setState(StateEnum state)
    {
        this.state = state;
    }
    
    public StateEnum getState()
    {
        return this.state;
    }
    
    /**
     * Public function to get integer input from display.
     * @return input as an integer
     */
    public int getIntInput()
    {
        int selection = input.nextInt();
        input.nextLine();     
        return selection;
    }
    
    /**
     * Public function to get string input from display.
     * @return input as a string
     */
    public String getStrInput()
    {
        return input.nextLine();
    }
    
    public double getDoubleInput()
    {
        return input.nextDouble();
    }
    
    public void Show(String str)
    {
        System.out.println(str);
    }
}//end display
