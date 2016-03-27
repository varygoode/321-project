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
            
            //if you register as employee you go to employye menu
            //if you register as customer you go to custormer menu
            
            //or it kicks you back to this menu and you have to login?
            
            System.out.println("3. Search"); //defaults to customer Menu
            System.out.println("4. Quit"); //endProgram
            System.out.println("Please enter the digit to make a selection.");
           
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
                    //User user = hms.allUsers.get(0);
                    //user.Login(ArrayList<User> userList, String name, String password);
                    //access to login needs work
                    break;
                case 2:
                    //User newUser = hms.allUsers.get(0);
                    //newUser.Register();
                    break;
                case 3:
                    setState(StateEnum.SEARCH);
                    break;
                case 4:
                    setState(StateEnum.QUIT);
                    break;
                
            }
            update();
            
    }//end public function
    
    private void custMenu()
    {
            System.out.println("**CUSTOMER MENU**");
            System.out.println("=================");
            System.out.println("1. Search/Reserve"); //takes you to search menu
            System.out.println("2. Cancel");
            System.out.println("Please enter the digit to make a selection.");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
                    setState(StateEnum.SEARCH);
                    
                    break;
                case 2:
                    setState(StateEnum.QUIT);
                    break;
                
            }
            update();
    }
    
    private void empMenu()
    {
            System.out.println("**EMPLOYEE MENU**");
            System.out.println("=================");
            System.out.println("1. Search"); //more search options? 
            System.out.println("2. Reserve"); //takes you to reservation menu
            System.out.println("3. Alter Room"); 
            System.out.println("4. Check-In"); 
            System.out.println("5. Check-Out");
            System.out.println("6. Report"); //for now prints 'database'
            System.out.println("7. Cancel");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
                    setState(StateEnum.SEARCH);
                    break;
                case 2:
  
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    setState(StateEnum.QUIT);
                    break;
                
            }
            update();
    }
    
    private void searchMenu()
    {
        //may need a separate search menu for employee and another for customer
            System.out.println("**SEARCH**");
            System.out.println("==========");
            System.out.println("1. Room"); //filters to room data will be applied 
            System.out.println("2. Make a Reservation"); //takes you to Reservation Menu
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
            
                    break;
                case 2:
  
                    break;
                case 3:
                    setState(StateEnum.QUIT);
                    break;
            }        
            update();
    }
    
    private void reservationMenu()
    {
            System.out.println("**Reservation Menu**");
            System.out.println("====================");
            System.out.println("1. Reserve Room");
            System.out.println("2. Confirm Reservation");
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
           
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
            
                    break;
                case 2:
  
                    break;
                case 3:
                    setState(StateEnum.QUIT);
                    break;
            }
            update();
    }
    
    private void checkInMenu()
    {
            System.out.println("**Check-In Menu**");
            System.out.println("=================");
            System.out.println("1. Select Date Duration");
            System.out.println("2. Enter Payment information"); //hold data for checkout
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
            
                    break;
                case 2:
  
                    break;
                case 3:
                    setState(StateEnum.QUIT);
                    break;
            }
            update();
    }
    
    private void checkOutMenu()
    {
            System.out.println("**Check-Out Menu**");
            System.out.println("==================");
            System.out.println("1. Select Date Duration");
            System.out.println("2. Enter Payment information"); //hold data for checkou
            System.out.println("3. Cancel");
            System.out.println("Please enter the digit to make a selection.");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
            
                    break;
                case 2:
  
                    break;
                case 3:
                    setState(StateEnum.QUIT);
                    break;
            }
            update();
    }
    
    private void cancelMenu()
    {
            System.out.println("**Cancel**");
            System.out.println("==========");
            System.out.println("Select an option from the list:");
            System.out.println("1. Return to Main Menu");
            System.out.println("2. Return to Search Menu");
            System.out.println("3. Quit"); //go to quitMenu
            System.out.println("Please enter the digit to make a selection.");
            
            menuOption = getIntInput();
            
            switch(menuOption)
            {
                case 1:
                    setState(StateEnum.MAIN);
                    break;
                case 2:
                    setState(StateEnum.SEARCH);
                    break;
                case 3:
                    setState(StateEnum.QUIT);
                    break;
            }
            update();
    }
    
    private void quitMenu()
    {
            System.out.println("=============");
            System.out.println("**Good Bye!**");
            System.out.println("=============");
            
            System.exit(0);
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
            case RESERVATION:
                reservationMenu();
                break;
            case CHECKIN:
                checkInMenu();
                break;
            case CHECKOUT:
                checkOutMenu();
                break;
            case CANCEL:
                cancelMenu();
                //setState(StateEnum.QUIT);
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
        String selection = input.next();
        input.nextLine();
        
        return selection;
    }
    
    public void Show(String str)
    {
        System.out.println(str);
    }
}//end display
