/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;
import java.util.*;

/**
 *
 * @author Monique
 */
public class Customer extends User 
{

    Reservation ResObj = new Reservation();
    Ledger LedgObj = new Ledger();
    ArrayList<Reservation> reserves = new ArrayList();
    
    Customer()
    {
        super();
    }
    
    Customer(String username, String password, String fName, String lName, int ID)
    {
        super(username, password, fName, lName, ID);
    }
  
    	
	public ArrayList<Reservation> findReservation(int ID) 
        {
	//Customer has already logged in and passed in the Array where reservation is stored
        //and the ArrayList containing username and password
                      
        return ledger.search(reserves, params);
            
	}
	
	public void cancel() 
        {
	/*
        code to return to HMS
        */
				
	}
    
        
        public void Reservation()
        {
            
            
        }
        }

