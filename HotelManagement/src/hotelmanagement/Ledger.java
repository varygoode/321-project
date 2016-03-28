/*
 * Class handles all search functionality.
 */
package hotelmanagement;

import java.util.ArrayList;

/**
 * Public class Ledger which will search through a given list for given parameters.
 * @author rob
 */
public class Ledger 
{    
    static private Ledger singletonLedger = null;
    
    private ArrayList<Reservation> filteredReservations;
    private ArrayList<Room> filteredRooms;
    private ArrayList<User> filteredUsers;
    
    private Ledger()
    {
        filteredReservations = new ArrayList();
        filteredRooms = new ArrayList();
        filteredUsers = new ArrayList();
    }
    
    public static Ledger getLedger()
    {
        if (singletonLedger == null) 
        {
            singletonLedger = new Ledger();       
        }    
        return singletonLedger;
    }
    
    /**
     * Public search function. Returns filtered ArrayList based on parameters.
     * @param searchList
     * @param searchParameters
     * @return 
     */
    public ArrayList search(ArrayList searchList, ArrayList<String> searchParameters)
    {
        if (searchList.get(0).getClass().equals(hotelmanagement.Reservation.class))
        {
            return searchReservations(searchList, searchParameters);
        }
        
        else if (searchList.get(0).getClass().equals(hotelmanagement.Room.class))
        {
            return searchRooms(searchList, searchParameters);
        }
        
        else if (searchList.get(0).getClass().getSuperclass().equals(hotelmanagement.User.class))
        {
            return searchUsers(searchList, searchParameters);
        }
        
        else
        {
            return null;
        }
    }
    
    private ArrayList searchReservations(ArrayList<Reservation> allReservations, ArrayList<String> searchParameters)
    {
        filteredReservations.clear(); //clear filtered list for fresh search
        
        for (Reservation res : allReservations)
        {
            //get all current reservation properties
            ArrayList<String> properties = new ArrayList();
            properties.add(Integer.toString(res.getRoom().getNumber()));
            properties.add(res.getStartDate().toString());
            properties.add(res.getEndDate().toString());
            properties.add(Integer.toString(res.getReserver().getID()));
            properties.add(Double.toString(res.getTotalPrice()));
            properties.add(String.valueOf(res.getIsPaid()));
            properties.add(Integer.toString(res.getReserveID()));
            
            //compare each search parameter to each property
            for (String parameter : searchParameters)
            {
                for (String property : properties)
                {
                    if (parameter.matches(property))
                    {
                        if(!filteredReservations.contains(res))
                        {
                            /*if there's a match and the reservation isn't
                            on the filtered list, add it */
                            filteredReservations.add(res);
                        }
                    }
                }
            }
            properties.clear(); //clear out the properties list, just in case
        }
        
        return filteredReservations;
    }
    
    private ArrayList searchRooms(ArrayList<Room> allRooms, ArrayList<String> searchParameters)
    {
        filteredRooms.clear(); //clear filtered list for fresh search
        
        for (Room room : allRooms)
        {
            //get all current room properties
            ArrayList<String> properties = new ArrayList();
            properties.add(Integer.toString(room.getNumber()));
            properties.add(room.getType().toString());
            properties.add(Double.toString(room.getRate()));
            
            //compare each search parameter to each property
            for (String parameter : searchParameters)
            {
                for (String property : properties)
                {
                    if (parameter.matches(property))
                    {
                        if(!filteredRooms.contains(room))
                        {
                            /*if there's a match and the room isn't
                            on the filtered list, add it */
                            filteredRooms.add(room);
                        }
                    }
                }
            }
            properties.clear(); //clear out the properties list, just in case
        }
        
        return filteredRooms;
    }
    
    private ArrayList searchUsers(ArrayList<User> allUsers, ArrayList<String> searchParameters)
    {
        filteredUsers.clear(); //clear filtered list for fresh search
        
        for (User user : allUsers)
        {
            //get all current user properties
            ArrayList<String> properties = new ArrayList();
            properties.add(Integer.toString(user.getID()));
            properties.add(user.getFirstName());
            properties.add(user.getLastName());
            properties.add(user.getUsername());
            
            //compare each search parameter to each property
            for (String parameter : searchParameters)
            {
                for (String property : properties)
                {
                    if (parameter.matches(property))
                    {
                        if(!filteredUsers.contains(user))
                        {
                            /*if there's a match and the user isn't
                            on the filtered list, add it */
                            filteredUsers.add(user);
                        }
                    }
                }
            }
            properties.clear(); //clear out the properties list, just in case
        }
        
        return filteredUsers;
    }
}
