/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.text.ParseException;

/**
 *
 * @author Rob
 */
public class testDriver {
    
    public static void main(String args[]) throws ParseException
    {
        HotelManagement hms = HotelManagement.getHMS();
        
        hms.initialize();
        
        hms.run();
    }
    
}
