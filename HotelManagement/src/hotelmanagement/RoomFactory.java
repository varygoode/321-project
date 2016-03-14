
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class RoomFactory 
{
    static private RoomFactory singletonFactory;
    
    private RoomFactory()
    {

    }
    
    public static RoomFactory getRoomFactory()
    {
        if (singletonFactory == null) 
        {
            singletonFactory = new RoomFactory();       
        }    
        return singletonFactory;
    }
            
    public Room createRoom(String type, int index, String description, double rate)
    {
        return new Room(type, index, description, rate);
    } 
    public void getMessage(){
    
        System.out.print("You made a room.");
    }
}
