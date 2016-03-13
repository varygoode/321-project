/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class ReservationFactory 
{
    static private ReservationFactory singletonFactory = new ReservationFactory();
    private Reservation reservation = null;
    
    private ReservationFactory()
    {
        System.out.print("Construction not allowed.");
    }
    
    public static ReservationFactory getReservationFactory()
    {
        return singletonFactory;
    }
            
    public Reservation getAReservation()
    {
        if (this.reservation == null) 
        {
            synchronized(this)
            {
                if(this.reservation == null)
                {
                    this.reservation = new Reservation();
                }
            }
        }    
        return this.reservation;
    } 
}
