/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.Scanner;

/**
 * @author andreapressnell
 */
public class Display 
{
    private Scanner input = new Scanner(System.in);
    private StateEnum state;
    
    public Display()
    {
        //constructor 
        state = StateEnum.MAIN;
    }
    
    private void startMenu()
    {            
            System.out.println("**MAIN MENU**");
            System.out.println("=============");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Search");
    }//end public function
    
    private void custMenu()
    {
        
    }
    
    private void empMenu()
    {
        
    }
    
    private void searchMenu()
    {
        
    }
    
    private void reservationMenu()
    {
        
    }
    
    private void checkInMenu()
    {
        
    }
    
    private void checkOutMenu()
    {
        
    }
    
    private void cancelMenu()
    {
        
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
                break;
            case QUIT:
                setState(StateEnum.MAIN);
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
}//end display
