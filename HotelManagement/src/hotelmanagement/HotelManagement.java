/*
 * 
 */
package hotelmanagement;

import java.util.ArrayList;
import java.util.Date;


public class HotelManagement
{
    ArrayList<Room> allRooms;
    ArrayList<User> allUsers;
    ArrayList<Reservation> allReserves;
    
    public HotelManagement() 
    {
        allRooms = new ArrayList();
        allUsers = new ArrayList();
        allReserves = new ArrayList();
    }
        
        public void initialize()
        {
            //===============================
            //  Factories
            //===============================
            //obtain the factory - Room
            RoomFactory roomFactory = RoomFactory.getRoomFactory();
            
            //obtain the factory - User
            UserFactory userFactory = UserFactory.getUserFactory();

            //obtain the factory - Reservation
            ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();
            
            //===============================
            //  Initialize Users
            //===============================
            allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user1","pass1","Andrew","Jackson",10001));
            allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user2","pass2","Martha","Washington",10002));
            allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user3","pass3","Harold","Truman",10003));
            allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user4","pass4","Barbara","Bush",10004));
            allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user5","pass5","Millard","Fillmore",10005));
            
            //===============================
            //  Initialize Rooms
            //===============================
            for (int i = 1; i<=3; i++)
            {
                for (int j = 0; j<=10; j++)
                {
                    allRooms.add(roomFactory.createRoom("Double Queen", (i*100)+j, "Two beautiful queen beds situated in a glorious simulated room. Perfect for divorced couples.", 89));
                }
            }
            
            //===============================
            //  Initialize Reservations
            //===============================
            allReserves.add(reservationFactory.createReservation(new Date(116,1,1), new Date(116,1,5), allRooms.get(5), true, allUsers.get(0)));
            allReserves.add(reservationFactory.createReservation(new Date(116,2,1), new Date(116,2,4), allRooms.get(10), true, allUsers.get(1)));
            allReserves.add(reservationFactory.createReservation(new Date(116,3,1), new Date(116,3,6), allRooms.get(15), true, allUsers.get(2)));
            allReserves.add(reservationFactory.createReservation(new Date(116,4,1), new Date(116,4,3), allRooms.get(20), true, allUsers.get(3)));
            allReserves.add(reservationFactory.createReservation(new Date(116,5,12), new Date(116,5,25), allRooms.get(25), true, allUsers.get(4)));
            
            
        
        }
}