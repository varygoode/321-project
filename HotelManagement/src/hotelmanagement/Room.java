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
    private String type;
    private int number;
    private String description;
    private double rate;
    
    public Room()
    {
        String type = null;
        int number = 0;
        String description = null;
        double rate = 0.0;
    }
    
    public Room(String ty, int idx, String desc, double rt)
    {
        String type = ty;
        int number = idx;
        String description = desc;
        double rate = rt;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
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
    
}
