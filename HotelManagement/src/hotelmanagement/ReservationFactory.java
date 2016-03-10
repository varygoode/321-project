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
    public ReservationFactory()
    {
        
    }
    
    public Reservation createReservation()
    {
        return new Reservation();    
    } 
}
