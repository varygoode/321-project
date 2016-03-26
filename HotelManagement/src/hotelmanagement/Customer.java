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

    ReservationFactory resFactory = ReservationFactory.getReservationFactory();
    Ledger LedgObj = Ledger.getLedger();
    
    ArrayList<Reservation> reserves;
    
    Customer()
    {
        super();
    }
    
    Customer(String username, String password, String fName, String lName, int ID)
    {
        super(username, password, fName, lName, ID);
    }
  
    	
    public Reservation findReservation(ArrayList<Reservation> reserveList, int ID) 
    {
        //Customer has already logged in and passed in the Array where reservation is stored
        //and the ArrayList containing username and password
        ArrayList<String> paramList = new ArrayList();
        paramList.add(Integer.toString(ID));
        ArrayList<Reservation> returnList = LedgObj.search(reserveList, paramList);      
        Reservation returnRes = returnList.get(0);
        return returnRes;
    }
	
    public void cancel() 
    {
    /*
    code to return to HMS
    */

    }


    public Reservation makeReservation(Date sDate, Date eDate, Room room, boolean payment, User user, int ID)
    {
        Reservation ResObj = resFactory.createReservation(sDate, eDate, room, payment, user, ID);

        return ResObj;
    }
}

