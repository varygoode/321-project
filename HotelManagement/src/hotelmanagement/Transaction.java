/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import newExceptions.*;

/**
 *
 * @author James
 */
public class Transaction {
    
    
    public Transaction()
    {
    }
    /*
    * @Param res Reservation to be paid
    * @Param payment The amount of the current payment
    */
    private boolean CollectPay(Reservation res, double payment) throws InsufficientFundsException
    {
        if(payment < res.getCurrentPrice())
        {
            double newPrice = res.getCurrentPrice() - payment;
            res.setCurrentPrice(newPrice);            
            throw new InsufficientFundsException();
        }
        else
        {
            res.setCurrentPrice(0.00);
            res.setPaid(true);
            return true;
        }

    }
    
    
    
    
    /*
    * Function checks the current date vs the Reservation parameter's 
    * date range. Throws a dateRange exception if it is not in
    * @Param res is a reservation object to be checked in
    */
    public void CheckIn(Reservation res) throws DateOutOfRangeException
    {

        Date today = new Date();
        if(today.after(res.getEndDate()) || (today.before(res.getStartDate()))) // cant check in if the current date is not in the reservations date range
        {
            throw new DateOutOfRangeException();
        }
        
        res.setCheckedIn(true);
        
    }
    
    /*
    * Function calls pay() and adds the reservation to the archives when payment is verified.
    * It DOES NOT delete the reservation from the reservation list as of right now.
    */
    public boolean CheckOut(Reservation res, double Payment)
    {
        //collect payment
        try 
        {
            CollectPay(res, Payment);
        } 
        catch (InsufficientFundsException ex) 
        {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
           
        //archive reservation
        Archive.getArchive().archiveThisReservation(res);
        
        return true;
    }
    

}
