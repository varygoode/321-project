/*
 * 
 */
package hotelmanagement;




public class HotelManagement{
    

    public static void main(String[] args) {
        
        
    //===============================
    //  Factories
    //===============================
        //obtain the factory - Reservation
        ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();
        reservationFactory.getMessage();
        
        //obtain the factory - Room
        RoomFactory roomFactory = RoomFactory.getRoomFactory();
        roomFactory.getMessage();
        
        //obtain the factory - User
        UserFactory userFactory = UserFactory.getUserFactory();
        userFactory.getMessage();
        
        
    }
}