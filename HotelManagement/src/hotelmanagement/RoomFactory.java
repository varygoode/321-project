/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;


public class RoomFactory {

    static private RoomFactory singletonFactory = new RoomFactory();

    private Room room = null;

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

    public void getMessage(){
    
        System.out.print("You made a room");
    }
    
    public Room createRoom(String type, int number, String description, double rate)
    {
        return new Room();
    }
}
