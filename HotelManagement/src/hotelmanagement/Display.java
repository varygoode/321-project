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
public class Display {
    
    public Display(){
    //constructor 
    }
    

private Scanner input = new Scanner(System.in);
    
    public void startMenu(){

            
            System.out.println("Select the number items from the menu below:");
            System.out.println("1. Go to Main Menu");
            System.out.println("2. Search a Room");
            System.out.println("3. Make a Reservation");
            System.out.println("4. Check-In");
            System.out.println("5. Check-Out");
            
            int selection = input.nextInt();
            input.nextLine();
         
            switch(selection){
                case 1:
                    //do main menu
                    break;

                case 2:
                    //do search
                    break;

                case 3:
                    //make reservation
                    break;

                case 4:
                    //checkin
                    break;
                    
                case 5:
                    //checkout
                    break;
                default:
                    System.out.println("Invalid selection.");
                    break;
            }//end of switch
    }//end public function
}//end display
