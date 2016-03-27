/*
 * 
 */
package hotelmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import newExceptions.DateOutOfRangeException;


public class HotelManagement
{
    static private HotelManagement singletonHMS = null;
    
    ArrayList<Room> allRooms;
    ArrayList<User> allUsers;
    ArrayList<Reservation> allReserves;
    Ledger theLedger;
    Display display;
    User currentUser;
    RoomFactory roomFactory;
    UserFactory userFactory;
    ReservationFactory reservationFactory;
    DateFormat df;
    ArrayList<Room> roomResults;
    ArrayList<Reservation> resResults;
    Transaction txn;
    Archive theArchive;
    
    private HotelManagement()
    {
        allRooms = new ArrayList();
        allUsers = new ArrayList();
        allReserves = new ArrayList();
        theLedger = Ledger.getLedger();
        display = new Display();
        currentUser = new User();
        roomFactory = RoomFactory.getRoomFactory();
        userFactory = UserFactory.getUserFactory();
        reservationFactory = ReservationFactory.getReservationFactory();
        df = new SimpleDateFormat("dd/MM/yyyy");
        roomResults = null;
        resResults = null;
        theArchive = Archive.getArchive();
    }
    
    public static HotelManagement getHMS()
    {
        if (singletonHMS == null) 
        {
            singletonHMS = new HotelManagement();       
        }    
        return singletonHMS;
    }
        
    public void initialize()
    {
        //===============================
        //  Initialize Users
        //===============================
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user1","pass1","Andrew","Jackson",10001));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user2","pass2","Martha","Washington",10002));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user3","pass3","Harold","Truman",10003));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user4","pass4","Barbara","Bush",10004));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user5","pass5","Millard","Fillmore",10005));
        
        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl1", "epass1", "John", "Oliver", 11001));
        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl2", "epass2", "Morgan", "Freemon", 11002));
        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl3", "epass3", "Meryl", "Streep", 11003));
        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl4", "epass4", "Cate", "Blanchett", 11004));
        
        
        //===============================
        //  Initialize Rooms
        //===============================
        for (int i = 1; i<=3; i++)
        {
            for (int j = 0; j<=10; j++)
            {
                if( (i+j) % 4 == 0)
                {
                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEKING, (i*100)+j, "A single king bed situated in a magnificent simulated suite. Perfect for the average-sized American.", 189));
                }
                else if((i+j) % 3 == 2)
                {
                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEKING, (i*100)+j, "A single king bed with plush pillows and exotic drapery situated in a romantic simulated suite. Perfect for the consummation.", 289));
                }
                else if((i*i+j*j) % 15 == 0)
                {
                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEQUEEN, (i*100)+j, "Amazing comfort & any bed, at your request, situated in an incredible simulated suite. Perfect for the top 1%.", 289));
                }
                else
                {
                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEQUEEN, (i*100)+j, "Two beautiful queen beds situated in a glorious simulated room. Perfect for divorced couples.", 89));
                }
            }
        }

        //===============================
        //  Initialize Reservations
        //===============================
        //  HARDCODED DATES MUST BE SET TO THE CURRENT DATE TO AVOID TRANSACTION EXCEPTION
        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(5), true, allUsers.get(0), 1000000));
        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(10), true, allUsers.get(1), 1000001));
        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(15), true, allUsers.get(2), 1000002));
        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(20), true, allUsers.get(3), 1000003));
        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(25), true, allUsers.get(4), 1000004));
    }
    
    public void run() throws ParseException, DateOutOfRangeException
    {
        display.update();
        boolean endProgram = false;
        while(!endProgram)
        {
            switch(display.getState())
            {
                case MAIN:
                    startMenu();
                    break;
                case CUSTOMER:
                    custMenu();
                    break;
                case EMPLOYEE:
                    empMenu();
                    break;
                case SEARCH:
                    searchMenu();
                    break;
//                case RESERVATION:
//                    reservationMenu();
//                    break;
                case CHECKIN:
                    checkInMenu();
                    break;
                case CHECKOUT:
                    checkOutMenu();
                    break;
                case CANCEL:
                    cancelMenu();
                    break;
                case QUIT:
                    endProgram = true;
                    if (endProgram==true)
                    {   
                      display.setState(StateEnum.QUIT);
                      System.exit(0);
                    }
                    break;
                default:
                    startMenu();
                    break;
            }
            
            display.update();
        }

    }
    
    public void Register()
    {
        display.Show("1. Register as Customer");
        display.Show("2. Register as Employee");
        display.Show("Please enter the digit to make a selection.");
        
        int userType = display.getIntInput();
        
        display.Show("Enter username:");
        String username = display.getStrInput();
        display.Show("Enter password:");
        String password = display.getStrInput();
        display.Show("Enter first name:");
        String fName = display.getStrInput();
        display.Show("Enter last name:");
        String lName = display.getStrInput();
        
        int ID = (allUsers.isEmpty()) ? 10000 : allUsers.get(allUsers.size() - 1).getID() + 1;
        
        User tempUser = null;
        
        switch(userType)
        {
            case 1:
            {
                tempUser = registerCustomer(username, password, fName, lName, ID);
            }
            break;
            case 2:
            {
                tempUser = registerEmployee(username, password, fName, lName, ID);
            }
            break;
        }
        
        allUsers.add(tempUser);
        currentUser = tempUser;
    }
    
    public Customer registerCustomer(String username, String password, String fName, String lName, int ID)
    {
        return new Customer(username, password, fName, lName, ID);
    }
    
    public Employee registerEmployee(String username, String password, String fName, String lName, int ID)
    {
        return new Employee(username, password, fName, lName, ID);
    }
    
    public User login()
    {
        display.Show("Enter your username:");
        String inUsername = display.getStrInput();
        ArrayList<String> params = new ArrayList<String>();
        params.add(inUsername);
        ArrayList<User> results = theLedger.search(allUsers, params);
        User tempUser = (results == null || results.isEmpty()) ? currentUser : results.get(0);
        
        display.Show("Enter your password:");
        String inPassword = display.getStrInput();
        
        if(tempUser.getUsername().equals(inUsername) && tempUser.getPassword().equals(inPassword))
        {
            return tempUser;
        }
        
        return currentUser;
    }
    
    private void startMenu()
    {            
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
            {
                if(currentUser.getID() == 9999)
                {
                    boolean finishLogin = false;
                    while(!finishLogin)
                    {
                        currentUser = login();
                        if(currentUser.getID() == 9999)
                        {
                            display.Show("Login failed.");
                            display.Show("1. Retry Login");
                            display.Show("2. Cancel login");
                            display.Show("Please enter the digit to make a selection.");
                            int selection = display.getIntInput();

                            if(selection == 2)
                            {
                                display.setState(StateEnum.MAIN);
                                finishLogin = true;
                            }
                        }
                        else
                        {
                            finishLogin = true;
                        }
                    }

                    if(currentUser.getID() != 9999)
                    {
                        display.Show("Success! Hello, " + currentUser.getFirstName() + "!");

                        if(currentUser.getClass() == Customer.class)
                        {
                            display.setState(StateEnum.CUSTOMER);
                        }
                        else if(currentUser.getClass() == Employee.class)
                        {
                            display.setState(StateEnum.EMPLOYEE);
                        }
                        else
                        {
                            display.Show("Couldn't recognize user type!");
                            display.setState(StateEnum.CUSTOMER);
                        }
                    }
                }
                else
                {
                    display.Show("You are already logged in, " + currentUser.getFirstName() + "!");
                    
                    if(currentUser.getClass() == Customer.class)
                    {
                        display.setState(StateEnum.CUSTOMER);
                    }
                    else if(currentUser.getClass() == Employee.class)
                    {
                        display.setState(StateEnum.EMPLOYEE);
                    }
                }
            }
                break;
            case 2:
            {
                if(currentUser.getID() == 9999)
                {
                    Register();

                    if(currentUser.getClass().equals(hotelmanagement.Employee.class))
                    {
                        display.setState(StateEnum.EMPLOYEE);
                    }
                    else
                    {
                        display.setState(StateEnum.CUSTOMER);
                    }

                    display.Show("Thank you for registering, " + currentUser.getFirstName() + "!");
                }
                else
                {
                    display.Show("You are already registered, " + currentUser.getFirstName() + "!");
                    
                    if(currentUser.getClass() == Customer.class)
                    {
                        display.setState(StateEnum.CUSTOMER);
                    }
                    else if(currentUser.getClass() == Employee.class)
                    {
                        display.setState(StateEnum.EMPLOYEE);
                    }
                }
            }
                break;
            case 3:
            {    
                display.setState(StateEnum.SEARCH);
            }
                break;    
            case 4:
            {    
                display.setState(StateEnum.QUIT);
            }
                break;    

        }            
    }
    
    private void custMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
            {
                
            }
                break; 
            case 2:
            {    
                                
            }   
                break; 
            case 3:
            {    
                display.setState(StateEnum.QUIT);                
            }   
                break; 
        }
    }
    
    private void empMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
            {
                display.setState(StateEnum.SEARCH);                
            }  
                break;  
            case 2:
            {
 
                display.Show("Which room would you like to alter?");
                display.Show("Please enter the room number to alter.");
                
                
            }
                break;
            case 3:
            {   
                display.setState(StateEnum.CHECKIN);                
            }
                break;
            case 4:
            {   
                display.setState(StateEnum.CHECKOUT);
            }
                break;
            case 5:
            {   
                display.Show("=*=*=*=*=*=*=*=*=*=*=*=*=*=NOTICE*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                display.Show("This will print a report based on all completed reservations.");
                display.Show("It does not include data from in-progress or future reservations.");
                display.Show("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                
                display.Show("\n\nChoose an option below:");
                display.Show("1. Report by room type and month");
                display.Show("2. All-Time Report");
                int choice = display.getIntInput();
                
                switch(choice)
                {
                    case 1:
                    {
                        display.Show("Enter room type:");
                        RoomTypeEnum type = RoomTypeEnum.valueOf(display.getStrInput());
                        display.Show("Enter month by number (Jan = 1, Feb = 2, ... Dec = 12):");
                        int month = display.getIntInput();
                        
                        display.Show("**" + (new DateFormatSymbols().getMonths()[month-1]).toUpperCase() + " REPORT**");
                        display.Show("=================");
                        display.Show("Most Occupied Room: " + theArchive.getReport().getMostOccupied(type, month).toString());
                        display.Show("Total Checkins: " + theArchive.getReport().getTotalCheckins(type, month));
                        display.Show("Total Income: " + theArchive.getReport().getTotalIncome(type, month));
                    }
                        break;
                    case 2:
                    {
                        display.Show("**ALL-TIME REPORT**");
                        display.Show("=================");
                        display.Show("Most Occupied Room: " + theArchive.getReport().getMostOccupiedRoom().toString());
                        display.Show("Most Occupied Room Amount: " + theArchive.getReport().getMostOccupiedRoomAmount());
                        display.Show("Total Checkins: " + theArchive.getReport().getTotalCheckins());
                        display.Show("Total Income: " + theArchive.getReport().getTotalIncome());
                    }
                        break;
                }
                
                
            }
                break;
            case 6:
            {
                display.setState(StateEnum.MAIN);
            }
                break;
            case 7:
            {
                display.setState(StateEnum.QUIT);
            }
                break;    

        }
    }
    
    private void searchMenu() throws ParseException
    {
        int menuOption = display.getIntInput();
        ArrayList<Reservation> reserveResults = null;
            
        switch(menuOption)
        {
            case 1:
            {
                ArrayList<String> params = new ArrayList<String>();
                String strInput = "";
                while(!strInput.equals("#"))
                {
                    display.Show("Input a search parameter, then press Enter. Type # and Enter when finished.");
                    strInput = display.getStrInput();
                    if(!strInput.equals("#"))
                    {
                        params.add(strInput);
                    }
                }
                display.Show("Your search is processing!");
                display.Show("Search Results:");
                
                roomResults = theLedger.search(allRooms, params);
                
                if(roomResults == null || roomResults.isEmpty())
                {
                    display.Show("No results!");
                }
                else
                {
                    for(Room room : roomResults)
                    {
                        display.Show("=========================");
                        display.Show("Result #" + roomResults.indexOf(room) + ":");
                        display.Show(room.toString());
                        display.Show("=========================");
                    }
                }
            }
                break;
            case 2:
            {
                if(currentUser.getClass() == hotelmanagement.User.class)
                {
                    display.Show("You must be logged in to make a reservation!");
                    display.Show("Return to the main menu to login, then search again.");
                }                
                else if(roomResults != null && !roomResults.isEmpty())
                {
                    String response = "";
                    Room roomToReserve = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Based on your latest search, enter the result # for the room you wish to book.");
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < roomResults.size())
                        {
                            display.Show("=========================");
                            display.Show(roomResults.get(resultIndex).toString());
                            display.Show("=========================");
                            display.Show("Is the above the correct room? Y/N");
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                roomToReserve = roomResults.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(roomResults.size() - 1) + "]");
                        }
                    }
                    
                    boolean reservationComplete = false;
                    while(!reservationComplete)
                    {
                        display.Show("When would you like to reserve the room from? (DD/MM/YYYY)");
                        String reserveDate1 = display.getStrInput();
                        display.Show("When would you like to reserve the room until? (DD/MM/YYYY)");
                        String reserveDate2 = display.getStrInput();
                        Date d1 = df.parse(reserveDate1);
                        Date d2 = df.parse(reserveDate2);
                        
                        ArrayList<Reservation> reservesMatchingDate = new ArrayList<Reservation>();
                        
                        if(d2.before(d1))
                        {
                            display.Show("Your end date is before your start date. Please try again.");
                        }
                        else
                        {
                            ArrayList<Date> datesInRange = getDatesBetween(d1, d2);
                            ArrayList<String> dateParams = new ArrayList<String>();
                            for(Date date : datesInRange)
                            {
                                dateParams.add(date.toString());
                            }
                            
                            reservesMatchingDate = theLedger.search(allReserves, dateParams);
                            
                            if(reservesMatchingDate.isEmpty())
                            {
                                display.Show("Your date is available for the chosen room.");
                                Reservation tempRes = reservationFactory.createReservation(d1, d2, roomToReserve, false, currentUser, allReserves.get(allReserves.size() - 1).getReserveID() + 1);
                                display.Show("=========================");
                                display.Show("Reservation Details:");
                                display.Show(tempRes.toString());
                                display.Show("=========================");
                                display.Show("Confirm reservation? Y/N");
                                String confirm = display.getStrInput();
                                
                                if(confirm.matches("Y") || confirm.matches("y"))
                                {
                                    allReserves.add(tempRes);
                                    reservationComplete = true;
                                    display.Show("Thank you! Your reservation has been confirmed with reservation number " + tempRes.getReserveID() + ".");
                                    display.Show("We look forward to your stay!");
                                }
                            }
                            else
                            {
                                display.Show("Your dates are not available for the chosen room. Please select different dates.");
                            }
                        }
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a room before trying to make a reservation.");
                }
            }
                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
//    private void reservationMenu() throws ParseException
//    {
//        ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();
//        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        int menuOption = display.getIntInput();
//
//        switch(menuOption)
//        {
//            case 1:
//            {
//                display.Show("When would you like to reserve the room from? (DD/MM/YYYY)");
//                String reserveDate1 = display.getStrInput();
//                display.Show("When would you like to reserve the room until? (DD/MM/YYYY)");
//                String reserveDate2 = display.getStrInput();
//                Date d1 = df.parse(reserveDate1);
//                Date d2 = df.parse(reserveDate2);
//                Reservation newRes = reservationFactory.createReservation(d1, d2, allRooms.get(5), true, allUsers.get(0), 1000000);
//                allReserves.add(newRes);
//                break;
//            }
//            case 2:
//            {
//                display.setState(StateEnum.MAIN);
//                break;
//            }
//            case 3:
//            {
//                display.setState(StateEnum.QUIT);
//                break;
//            }
//        }
//    }
//    
    private void checkInMenu() throws DateOutOfRangeException
    {
        txn = new Transaction();
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
            {
                for(Reservation res : allReserves)
                {
                    display.Show("=========================");
                    display.Show("Result #" + allReserves.indexOf(res) + ":");
                    display.Show(res.toString());
                    display.Show("=========================");
                }
                
                if(currentUser.getClass() == hotelmanagement.User.class)
                {
                    display.Show("You must be logged in to check in!");
                    display.Show("Return to the main menu to login, then search again.");
                }                
                else if(allReserves != null && !allReserves.isEmpty())
                {
                    String response = "";
                    Reservation resCheckin = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Based on your latest search, enter the result # for the reservation.");
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < allReserves.size())
                        {
                            display.Show("=========================");
                            display.Show(allReserves.get(resultIndex).toString());
                            display.Show("=========================");
                            display.Show("Is the above the correct room? Y/N");
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                resCheckin = allReserves.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(allReserves.size() - 1) + "]");
                        }
                    }
                    
                    display.Show("=========================");
                    display.Show("Reservation Details:");
                    display.Show(resCheckin.toString());
                    display.Show("=========================");
                    display.Show("Confirm reservation? Y/N");
                    String confirm = display.getStrInput();

                    if(confirm.matches("Y") || confirm.matches("y"))
                    {
                        txn.CheckIn(resCheckin);
                        resCheckin.setCheckedIn(true);
                        display.Show("Thank you! Your reservation has been checked in with reservation number " + resCheckin.getReserveID() + ".");
                        display.Show("Enjoy your stay!");
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a reservation before trying to check in.");
                }
                
                //txn.CheckIn(res);
            }
            break;
            case 2:
            {
                display.setState(StateEnum.MAIN);
            }
            break;
            case 3:
            {
                display.setState(StateEnum.QUIT);
            }
            break;
        }
    }
    
    private void checkOutMenu() throws ParseException
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
            {
                display.Show("By which parameter would you like to search?");
                display.Show("1. Reservation ID");
                display.Show("2. User ID");
                display.Show("3. Room Number");
                display.Show("4. Reservation Date Range");
                int subMenuOption = display.getIntInput();
                
                ArrayList<String> params = new ArrayList<String>();
                
                switch(subMenuOption)
                {
                    case 1:
                    case 2:
                    case 3:
                    {
                        String strInput = "";
                        while(!strInput.equals("#"))
                        {
                            display.Show("Input a search parameter, then press Enter. Type # and Enter when finished.");
                            strInput = display.getStrInput();
                            if(!strInput.equals("#"))
                            {
                                params.add(strInput);
                            }
                        }
                        
                        resResults = theLedger.search(allReserves, params);
                    }
                    break;
                    case 4:
                    {
                        display.Show("Start Date? (DD/MM/YYYY)");
                        String reserveDate1 = display.getStrInput();
                        display.Show("End Date? (DD/MM/YYYY)");
                        String reserveDate2 = display.getStrInput();
                        Date d1 = df.parse(reserveDate1);
                        Date d2 = df.parse(reserveDate2);
                        
                        params.add(d1.toString());
                        params.add(d2.toString());
                        
                        resResults = theLedger.search(allReserves, params);
                        
                        for (Reservation res : resResults)
                        {
                            if(res.getStartDate().equals(d1))
                            {
                                if (!res.getEndDate().equals(d2))
                                {
                                    resResults.remove(res);
                                }
                            }
                            
                            if(res.getEndDate().equals(d2))
                            {
                                if (!res.getStartDate().equals(d1))
                                {
                                    resResults.remove(res);
                                }
                            }
                        }
                    }
                        break;
                }
                
                if(resResults == null || resResults.isEmpty())
                {
                    display.Show("No results!");
                }
                else
                {
                    for(Reservation res : resResults)
                    {
                        display.Show("=========================");
                        display.Show("Result #" + resResults.indexOf(res) + ":");
                        display.Show(res.toString());
                        display.Show("=========================");
                    }
                }
            }
                break;
            case 2:
            {
                if(resResults != null && !resResults.isEmpty())
                {
                    String response = "";
                    Reservation reserveToCheckout = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Based on your latest search, enter the result # for the reservation you wish to check-out.");
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < resResults.size())
                        {
                            display.Show("=========================");
                            display.Show(resResults.get(resultIndex).toString());
                            display.Show("=========================");
                            display.Show("Is the above the correct reservation? Y/N");
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                reserveToCheckout = resResults.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(roomResults.size() - 1) + "]");
                        }
                    }
                    
                    boolean checkoutComplete = false;
                    boolean partialPayment = false;
                    while(!checkoutComplete)
                    {
                        display.Show("The remaining bill for this reservation is $" + reserveToCheckout.getCurrentPrice());
                        display.Show("Enter an amount to pay:");
                        double payAmt = Double.parseDouble(display.getStrInput());
                        Transaction trans = new Transaction();
                        if(!trans.CheckOut(reserveToCheckout, payAmt))
                        {
                            display.Show("Thank you for partial payment. You still owe $" + reserveToCheckout.getCurrentPrice());
                            display.Show("Payment must be in full to check-out.");
                            display.Show("Stop check-out and return to check-out menu?");
                            String returnInput = display.getStrInput();
                            if(returnInput.matches("Y") || returnInput.matches("y"))
                            {
                                display.Show("Current reservation check-out incomplete! Returning to check-out menu...");
                                partialPayment = true;
                            }
                        }
                        
                        checkoutComplete = (partialPayment) ? true : (reserveToCheckout.getCurrentPrice() <= 0.0);
                    }
                    
                    boolean reallyComplete = (!partialPayment) ? checkoutComplete : false;
                    if(reallyComplete)
                    {
                        reserveToCheckout.setCheckedIn(false);
                    }
                    
                    if(!reserveToCheckout.IsCheckedIn())
                    {
                        allReserves.remove(reserveToCheckout);
                        display.Show("Check-out complete for reservation #" + reserveToCheckout.getReserveID());
                    }
                    else
                    {
                        display.Show("Check-out incomplete for reservation #" + reserveToCheckout.getReserveID());
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a reservation before trying to make a check-out.");
                }
            }
                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private void cancelMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
                display.setState(StateEnum.MAIN);
                break;
            case 2:
                display.setState(StateEnum.SEARCH);
                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private ArrayList<Date> getDatesBetween(Date start, Date end)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(start);

        while (calendar.getTime().before(end))
        {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        
        dates.add(calendar.getTime());
        
        return dates;
    }
}
