
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class ReservationFactory 
{
    static private ReservationFactory singletonFactory;
    private Reservation reservation;
    
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
            
    public Reservation createReservation(int room, double price, boolean payment, User user)
    {
        return new Reservation(room, price, payment, user);
    } 
    public void getMessage(){
    
        System.out.print("You made a reservation.");
    }
}
