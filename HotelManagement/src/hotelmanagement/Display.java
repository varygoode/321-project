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
    
    public Display()
    {
        //constructor 
    }
    
    public void startMenu()
    {            
            System.out.println("**MAIN MENU**");
            System.out.println("=============");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Search");
            
            int selection = input.nextInt();
            input.nextLine();
         
            switch(selection)
            {
                case 1:
                    //do login
                    break;

                case 2:
                    //do register
                    break;

                case 3:
                    //do search
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }//end of switch
    }//end public function
}//end display
