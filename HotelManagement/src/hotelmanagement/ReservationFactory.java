
package hotelmanagement;

import java.util.Date;

/**
 *
 * @author timothy
 */
public class ReservationFactory 
{
    static private ReservationFactory singletonFactory;
    
    private ReservationFactory()
    {

    }
    
    public static ReservationFactory getReservationFactory()
    {
        if (singletonFactory == null) 
        {
            singletonFactory = new ReservationFactory();       
        }    
        return singletonFactory;
    }
            
    public Reservation createReservation(Date sDate, Date eDate, Room room, boolean payment, User user, int ID)
    {
        return new Reservation(sDate, eDate, room, payment, user, ID);
    } 
    public void getMessage()
    {
        System.out.print("You made a reservation.");
    }
}
