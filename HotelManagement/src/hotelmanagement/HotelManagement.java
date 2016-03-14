/*
 * 
 */
package hotelmanagement;


public class HotelManagement{
        
        public void initialize(){
            
            int room = 101;
            double price = 30.95;
            boolean payment = false;
            User user = null;
            
            String ty = "queen";
            int idx = 1;
            String desc = "queen";
            double rt = 50.99;
            //===============================
            //  Factories
            //===============================
            //obtain the factory - Reservation
            ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();
            reservationFactory.getMessage();
            Reservation testres = reservationFactory.createReservation(room, price, payment, user);
            System.out.print(testres.getRoomNumber());
            

            //obtain the factory - Room
            RoomFactory roomFactory = RoomFactory.getRoomFactory();
            roomFactory.getMessage();
            Room testroom = roomFactory.createRoom(ty, idx, desc, rt);
            System.out.print(testroom.getNumber());

            //obtain the factory - User
            UserFactory userFactory = UserFactory.getUserFactory();
            userFactory.getMessage();
            //userFactory.createUser();
        
        }
}