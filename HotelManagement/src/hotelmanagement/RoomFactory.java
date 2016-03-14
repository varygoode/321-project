/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;


public class RoomFactory {

    static private RoomFactory roomFactory = new RoomFactory();

    private Room room = null;

    private RoomFactory() {
        //private constructor - don't want the class
        // to instantiated from other classes
    }
    
    public static RoomFactory getRoomFactory() {
        return roomFactory;
    }

    public void getMessage(){
    
        System.out.print("You made a room");
    }
    
    public Room createRoom(String type, int number, String description, double rate)
    {
        if (this.room == null) 
        {
            synchronized(this) 
            {
                if(this.room == null)
                {
                    this.room = new Room(type, number, description, rate);
                }
            } 
        }
        return this.room;
    }
}
