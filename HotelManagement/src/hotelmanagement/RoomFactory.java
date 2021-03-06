
package hotelmanagement;

/**
 *
 * @author timothy
 */


public class RoomFactory {

    static private RoomFactory singletonFactory;

    private RoomFactory() {
        //private constructor - don't want the class
        // to instantiated from other classes
    }
    
    public static RoomFactory getRoomFactory() {
        
        if (singletonFactory == null)
        {
            singletonFactory = new RoomFactory();
        }
        return singletonFactory;
    }
            
    public Room createRoom(RoomTypeEnum type, int roomNum, String description, double rate)
    {
        return new Room(type, roomNum, description, rate);
    } 
    public void getMessage(){
    
        System.out.print("You made a room");
    }
}
