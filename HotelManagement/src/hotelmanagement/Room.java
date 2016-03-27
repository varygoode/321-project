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
public class Room 
{
    private RoomTypeEnum type;
    private int number;
    private String description;
    private double rate;
    
    public Room()
    {
        type = null;
        number = 0;
        description = null;
        rate = 0.0;
    }
    
    public Room(RoomTypeEnum ty, int idx, String desc, double rt)
    {
        type = ty;
        number = idx;
        description = desc;
        rate = rt;
    }
    
    public RoomTypeEnum getType() {
        return type;
    }

    public void setType(RoomTypeEnum type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    public String toString()
    {
        return "Number: " + number + "\nType: " + type + "\nRate: " + Double.toString(rate) + "\nDescription: " + description;
    }
    
    public void AlterRoom(String ty, int idx, String desc, double rt)
    {   
        setType(ty);
        setNumber(idx);
        setDescription(desc);
        setRate(rt);
        toString();

    }

}
