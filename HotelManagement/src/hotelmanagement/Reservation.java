/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;
import java.util.Calendar;

/**
 *
 * @author timothy
 */
public class Reservation {
    
    private int roomNumber;
    private double totalPrice;
    private boolean isPaid;
    User reserver;
    
    public Reservation()
    {
        roomNumber = 000;
        totalPrice = 0.00;
        isPaid = false;
        reserver = null;
    }
    
    public Reservation(int room, double price, boolean payment, User user)
    {
        roomNumber = room;
        totalPrice = price;
        isPaid = payment;
        reserver = user;
    }
 
    //===============================
    //  Mutators
    //===============================
    public void setRoomNumber(int room)
    {
        roomNumber = room;
    }
    
    public void setTotalPrice(double price)
    {
        totalPrice = price;
    }
    
    public void setPaid(boolean payment)
    {
        isPaid = payment;
    }
    
    public void setReserver(User user)
    {
        reserver = user;
    }
    
    //===============================
    //  Accessors
    //===============================
    public int getRoomNumber()
    {
        return roomNumber;
    }
    
    public double getTotalPrice()
    {
        return totalPrice;
    }
    
    public boolean getPaid()
    {
        return isPaid;
    }
    
    public User getReserver()
    {
        return reserver;
    }
    
}