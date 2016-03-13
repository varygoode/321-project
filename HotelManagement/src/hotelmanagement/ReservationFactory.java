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

    }
    
    public static ReservationFactory getReservationFactory()
    {
        return singletonFactory;
    }
            
    public Reservation getAReservation(int room, double price, boolean payment, User user)
    {
        if (this.reservation == null) 
        {
            synchronized(this)
            {
                if(this.reservation == null)
                {
                    this.reservation = new Reservation(room, price, payment, user);
                }
            }
        }    
        return this.reservation;
    } 
    public void getMessage(){
    
        System.out.print("You made a reservation.");
    }
}
