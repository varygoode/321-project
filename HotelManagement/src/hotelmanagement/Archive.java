/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;

import java.util.ArrayList;

/**
 *Archive is a singleton.
 * @author James
 */
public class Archive {
    
    
    /////////////////////////////   Setting up Singleton  /////////////////////////////////////
    //singleton
    private static Archive instance = null;
    public ArrayList<Reservation> TheArchives;//holds the list of all the used reservations
 
    
    private Archive()
    {
        TheArchives = new ArrayList();
    }
    
    public static Archive getArchive()
    {
        if(instance == null)
        {
            instance = new Archive();
        }
        return instance;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////
    

    
    
    boolean archiveThisReservation(Reservation reservation)
    {
       return (TheArchives.add(reservation));
    }
    
    //private Report getReport()
    {
       // return new Report(TheArchives);
    }
    
    
    
}
