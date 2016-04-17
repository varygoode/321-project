/*
 * 
 */
package hotelmanagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Database hotelDB;
    RoomTypeEnum type;
    
    private HotelManagement()
    {
        allRooms = new ArrayList();
        allUsers = new ArrayList();
        allReserves = new ArrayList();
        theLedger = Ledger.getLedger();
        display = new Display(this);
        currentUser = new User();
        roomFactory = RoomFactory.getRoomFactory();
        userFactory = UserFactory.getUserFactory();
        reservationFactory = ReservationFactory.getReservationFactory();
        df = new SimpleDateFormat("dd/MM/yyyy");
        roomResults = null;
        resResults = null;
        theArchive = Archive.getArchive();
        hotelDB = Database.getDB();
    }
    
    public static HotelManagement getHMS()
    {
        if (singletonHMS == null) 
        {
            singletonHMS = new HotelManagement();       
        }    
        return singletonHMS;
    }
        
    public void initialize() throws SQLException
    {
        
        //=================================================
        //  Initialize database and establish connection
        //=================================================
        
        hotelDB.startConnection();
        
        //===============================
        //  Initialize Users
        //===============================
//        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user1","pass1","Andrew","Jackson",10001));
//        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user2","pass2","Martha","Washington",10002));
//        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user3","pass3","Harold","Truman",10003));
//        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user4","pass4","Barbara","Bush",10004));
//        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user5","pass5","Millard","Fillmore",10005));
//        
//        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl1", "epass1", "John", "Oliver", 11001));
//        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl2", "epass2", "Morgan", "Freemon", 11002));
//        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl3", "epass3", "Meryl", "Streep", 11003));
//        allUsers.add(userFactory.createUser(hotelmanagement.Employee.class,"empl4", "epass4", "Cate", "Blanchett", 11004));
//        
          hotelDB.initUsers(allUsers, userFactory);

        
        //===============================
        //  Initialize Rooms
        //===============================
//        for (int i = 1; i<=3; i++)
//        {
//            for (int j = 0; j<=10; j++)
//            {
//                if( (i+j) % 4 == 0)
//                {
//                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEKING, (i*100)+j, "A single king bed situated in a magnificent simulated suite. Perfect for the average-sized American.", 189));
//                }
//                else if((i+j) % 3 == 2)
//                {
//                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEKING, (i*100)+j, "A single king bed with plush pillows and exotic drapery situated in a romantic simulated suite. Perfect for the consummation.", 289));
//                }
//                else if((i*i+j*j) % 15 == 0)
//                {
//                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEQUEEN, (i*100)+j, "Amazing comfort & any bed, at your request, situated in an incredible simulated suite. Perfect for the top 1%.", 289));
//                }
//                else
//                {
//                    allRooms.add(roomFactory.createRoom(RoomTypeEnum.ONEQUEEN, (i*100)+j, "Two beautiful queen beds situated in a glorious simulated room. Perfect for divorced couples.", 89));
//                }
//            }
//        }

            hotelDB.initRooms(allRooms, roomFactory);

        //===============================
        //  Initialize Reservations
        //===============================
        //  HARDCODED DATES MUST BE SET TO THE CURRENT DATE TO AVOID TRANSACTION EXCEPTION
//        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(5), true, allUsers.get(0), 1000000));
//        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(10), true, allUsers.get(1), 1000001));
//        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(15), true, allUsers.get(2), 1000002));
//        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(20), true, allUsers.get(3), 1000003));
//        allReserves.add(reservationFactory.createReservation(new Date(116,2,27), new Date(116,2,30), allRooms.get(25), true, allUsers.get(4), 1000004));

        hotelDB.initReservations(allReserves, allRooms, allUsers, reservationFactory, theLedger);
        hotelDB.initArchives(theArchive.TheArchives, roomResults, allUsers, reservationFactory, theLedger);
    }
    
    public void run() throws ParseException, DateOutOfRangeException, SQLException
    {
        boolean endProgram = false;
        //while(!endProgram)
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
                        hotelDB.storeUsers(allUsers);
                        hotelDB.storeRooms(allRooms);
                        hotelDB.storeReservations(allReserves, allRooms, allUsers);
                        hotelDB.storeArchives(theArchive.TheArchives, roomResults, allUsers);
                        hotelDB.closeConnection();
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
        display.Show("1. Register as Customer", true);
        display.Show("2. Register as Employee", true);
        
        int userType = display.getIntInput();
        
        display.Show("Enter username:", true);
        String username = display.getStrInput();
        display.Show("Enter password:", true);
        String password = display.getStrInput();
        display.Show("Enter first name:", true);
        String fName = display.getStrInput();
        display.Show("Enter last name:", true);
        String lName = display.getStrInput();
        
        int ID = (allUsers.isEmpty()) ? 10000 : allUsers.get(allUsers.size() - 1).getID() + 2;
        
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
        display.Show("Enter your username:", true);
        String inUsername = display.getStrInput();
        ArrayList<String> params = new ArrayList<String>();
        params.add(inUsername);
        ArrayList<User> results = theLedger.search(allUsers, params);
        User tempUser = (results == null || results.isEmpty()) ? currentUser : results.get(0);
        
        display.Show("Enter your password:", true);
        String inPassword = display.getStrInput();
        
        if(tempUser.getUsername().equals(inUsername) && tempUser.getPassword().equals(inPassword))
        {
            return tempUser;
        }
        
        return currentUser;
    }
    
    private void startMenu()
    {            
        int menuOption = display.getMenuInput();
        
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
                            display.Show("Login failed.", true);
                            display.Show("1. Retry Login", true);
                            display.Show("2. Cancel login", true);
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
                        display.Show("Success! Hello, " + currentUser.getFirstName() + "!", false);

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
                            display.Show("Couldn't recognize user type!", false);
                            display.setState(StateEnum.CUSTOMER);
                        }
                    }
                }
                else
                {
                    display.Show("You are already logged in, " + currentUser.getFirstName() + "!", false);

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

                    display.Show("Thank you for registering, " + currentUser.getFirstName() + "!", false);
                }
                else
                {
                    display.Show("You are already registered, " + currentUser.getFirstName() + "!", false);

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
        int menuOption = display.getMenuInput();

        switch(menuOption)
        {
            case 1:
            {
                display.setState(StateEnum.SEARCH);
            }
                break; 
            case 2:
            {    
                resResults = theLedger.search(allReserves, new ArrayList<String>(Arrays.asList(Integer.toString(currentUser.getID()))));
                
                if(resResults != null && !resResults.isEmpty())
                {
                    String resultsStr = "";
                    
                    for(Reservation res : resResults)
                    {
                        resultsStr += "=========================\n";
                        resultsStr += "Result #" + resResults.indexOf(res) + ":\n";
                        resultsStr += res.toString();
                        resultsStr += "\n=========================\n";
                    }
                    
                    display.ShowResults(resultsStr);
                    
                    display.Show("Based on your results, enter the result # for the reservation you wish to cancel.", true);
                    int resultIndex = display.getIntInput();
                    
                    String response = "";
                    Reservation resToCancel = null;
                    
                    while (!(response.matches("Y") || response.matches("y")))
                    {
                        if(resultIndex >= 0 && resultIndex < resResults.size())
                        {
                            display.Show("=========================", true);
                            display.Show(resResults.get(resultIndex).toString(), true);
                            display.Show("=========================", true);
                            display.Show("Is the above the correct room? Y/N", true);
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                resToCancel = resResults.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(resResults.size() - 1) + "]", false);
                        }
                    }
                    
                    if(resToCancel != null)
                    {
                        allReserves.remove(resToCancel);
                        display.Show("Reservation with ID #" + resToCancel.getReserveID() + " has been cancelled.", false);
                    }
                    else
                    {
                        display.Show("No reservation has been cancelled.", false);
                    }
                }
                else
                {
                    display.Show("You have no reservations!", false);
                }
            }   
                break;
            case 3:
            {    
                display.setState(StateEnum.CUSTOMER);
            }   
                break;
            case 4:
            {    
                display.setState(StateEnum.CANCEL);                
            }   
                break; 
        }
    }
    
    private void empMenu()
    {
        int menuOption = display.getMenuInput();

        switch(menuOption)
        {
            case 1:
            {
                display.setState(StateEnum.SEARCH);                
            }  
                break;  
            case 2:
            {
                String resultsStr = "";
                resultsStr += "Which room would you like to alter?\n";
                resultsStr += "Please enter the room number to alter.\n";
                //System.out.println(allRooms.toString(allRooms));
                 for(Room aroom : allRooms)
                {
                    resultsStr += "=========================\n";
                    resultsStr += "Result #" + allRooms.indexOf(aroom) + ":\n";
                    resultsStr += aroom.toString();
                    resultsStr += "\n=========================\n";
                }
                 
                display.ShowResults(resultsStr);
                
                if(currentUser.getClass() != hotelmanagement.Employee.class)
                {
                    display.Show("You must be logged in as an employee!", false);
                }                
                else if(allRooms != null && !allRooms.isEmpty())
                {
                    String response = "";
                    Room aroom = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Enter the # for the room you wish to alter.", true);
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < allRooms.size())
                        {
                            display.Show("=========================", true);
                            display.Show(allRooms.get(resultIndex).toString(), true);
                            display.Show("=========================", true);
                            display.Show("Is the above the room you wish to alter? Y/N", true);
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                RoomTypeEnum changeTy = null;
                                aroom = allRooms.get(resultIndex);
                                
                                display.Show("Please make a change to the room type.", true);
                                display.Show("Make a selection to change room type.", true);
                                display.Show("1. Change to one twin bed.", true);
                                display.Show("2. Change to one two twin beds.", true);
                                display.Show("3. Change to one full bed.", true);
                                display.Show("4. Change to two full bed.", true);
                                display.Show("5. Change to one queen bed.", true);
                                display.Show("6. Change to one king bed.", true);
                                
                                int alterOption = display.getIntInput();
                                
                                switch(alterOption)
                                {
                                    case 1:
                                    {
                                        changeTy = type.ONETWIN;
                                    }
                                        break; 
                                    case 2:
                                    {    
                                        changeTy = type.TWOTWIN;
                                    }   
                                        break; 
                                    case 3:
                                    {    
                                         changeTy = type.ONEFULL;               
                                    }   
                                        break;
                                    case 4:
                                    {
                                        changeTy = type.TWOFULL;
                                    }
                                        break; 
                                    case 5:
                                    {    
                                        changeTy = type.ONEQUEEN;
                                    }   
                                        break; 
                                    case 6:
                                    {    
                                         changeTy = type.ONEKING;               
                                    }   
                                        break;
                                }
                                
                                display.Show("Please make a change to the room number.", true);
                                int changeIdx = display.getIntInput();
                                
                                display.Show("Please make a change to the room description.", true);
                                String changeDesc = display.getStrInput();
                                
                                display.Show("Please make a change to the room rate.", true);
                                double changeRate = display.getDoubleInput();
                                
                                aroom.AlterRoom(changeTy, changeIdx, changeDesc, changeRate);                        
                                display.Show("===========================", true);
                                display.Show("You have altered this room.", true);
                                display.Show("===========================", false);
                            }
                            else
                            {
                                display.Show("===========================", true);
                                display.Show("No changes have been made.", true);
                                display.Show("===========================", false);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(allRooms.size() - 1) + "]", false);
                        }
                    }
                    
                    display.Show("==========================", true);
                    display.Show("Altered Room Details:", true);
                    display.Show(aroom.toString(), true);
                    display.Show("==========================", true);
                    display.Show("Returning to Employee Menu", true);
                    display.Show("==========================", false);
                }
                break;
            }
            
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
                display.Show("=*=*=*=*=*=*=*=*=*=*=*=*=*=NOTICE*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=", true);
                display.Show("This will print a report based on all completed reservations.", true);
                display.Show("It does not include data from in-progress or future reservations.", true);
                display.Show("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=", false);
                
                display.Show("Choose an option below:", true);
                display.Show("1. Report by room type and month", true);
                display.Show("2. All-Time Report", true);
                int choice = display.getIntInput();
                
                if(theArchive.TheArchives.isEmpty())
                {
                    display.Show("There are no Archives right now. You must check out some Reservations first.", false);
                }
                else
                {
                    switch(choice)
                    {
                        case 1:
                        {
                            display.Show("Enter room type:", true);
                            RoomTypeEnum type = RoomTypeEnum.valueOf(display.getStrInput());
                            display.Show("Enter month by number (Jan = 1, Feb = 2, ... Dec = 12):", true);
                            int month = display.getIntInput();

                            display.Show("**" + (new DateFormatSymbols().getMonths()[month-1]).toUpperCase() + " REPORT**", true);
                            display.Show("=================", true);
                            display.Show("Most Occupied Room: " + theArchive.getReport().getMostOccupied(type, month).toString(),true);
                            display.Show("Total Checkins: " + theArchive.getReport().getTotalCheckins(type, month), true);
                            display.Show("Total Income: " + theArchive.getReport().getTotalIncome(type, month), false);
                        }
                            break;
                        case 2:
                        {
                            display.Show("**ALL-TIME REPORT**", true);
                            display.Show("=================", true);
                            display.Show("Most Occupied Room: " + theArchive.getReport().getMostOccupiedRoom().toString(), true);
                            display.Show("Most Occupied Room Amount: " + theArchive.getReport().getMostOccupiedRoomAmount(), true);
                            display.Show("Total Checkins: " + theArchive.getReport().getTotalCheckins(), true);
                            display.Show("Total Income: " + theArchive.getReport().getTotalIncome(), true);
                        }
                            break;
                    }
                }
                
            }
                break;
            case 6:
            {
                display.setState(StateEnum.EMPLOYEE);
            }
                break;
            case 7:
            {
                display.setState(StateEnum.CANCEL);
            }
                break;    

        }
    }
    
    private void searchMenu() throws ParseException
    {
        int menuOption = display.getMenuInput();
        ArrayList<Reservation> reserveResults = null;
            
        switch(menuOption)
        {
            case 1:
            {
                ArrayList<String> params = new ArrayList<String>();
                String strInput = "";
                while(!strInput.equals("#"))
                {
                    display.Show("Input a search parameter, then press Enter. Type # and Enter when finished.", true);
                    strInput = display.getStrInput();
                    if(!strInput.equals("#"))
                    {
                        params.add(strInput);
                    }
                }
                String resultsStr = "Search Results:\n";
                
                roomResults = theLedger.search(allRooms, params);
                
                if(roomResults == null || roomResults.isEmpty())
                {
                    display.Show("No results!", false);
                }
                else
                {
                    for(Room room : roomResults)
                    {
                        resultsStr += "=========================\n";
                        resultsStr += "Result #" + roomResults.indexOf(room) + ":\n";
                        resultsStr += room.toString();
                        resultsStr += "\n=========================\n";
                    }
                    
                    display.ShowResults(resultsStr);
                }
            }
                break;
            case 2:
            {
                if(currentUser.getClass() == hotelmanagement.User.class)
                {
                    display.Show("You must be logged in to make a reservation!", true);
                    display.Show("Return to the main menu to login, then search again.", false);
                }                
                else if(roomResults != null && !roomResults.isEmpty())
                {
                    String response = "";
                    Room roomToReserve = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Based on your latest search, enter the result # for the room you wish to book.", true);
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < roomResults.size())
                        {
                            display.Show("=========================", true);
                            display.Show(roomResults.get(resultIndex).toString(), true);
                            display.Show("=========================", true);
                            display.Show("Is the above the correct room? Y/N", true);
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                roomToReserve = roomResults.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(roomResults.size() - 1) + "]", false);
                        }
                    }
                    
                    boolean reservationComplete = false;
                    while(!reservationComplete)
                    {
                        display.Show("When would you like to reserve the room from? (DD/MM/YYYY)", true);
                        String reserveDate1 = display.getStrInput();
                        display.Show("When would you like to reserve the room until? (DD/MM/YYYY)", true);
                        String reserveDate2 = display.getStrInput();
                        Date d1 = df.parse(reserveDate1);
                        Date d2 = df.parse(reserveDate2);
                        
                        ArrayList<Reservation> reservesMatchingDate = new ArrayList<Reservation>();
                        
                        if(d2.before(d1))
                        {
                            display.Show("Your end date is before your start date. Please try again.", false);
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
                                display.Show("Your date is available for the chosen room.", false);
                                Reservation tempRes = reservationFactory.createReservation(d1, d2, roomToReserve, false, currentUser, allReserves.get(allReserves.size() - 1).getReserveID() + 1);
                                display.Show("=========================", true);
                                display.Show("Reservation Details:", true);
                                display.Show(tempRes.toString(), true);
                                display.Show("=========================", true);
                                display.Show("Confirm reservation? Y/N", true);
                                String confirm = display.getStrInput();
                                
                                if(confirm.matches("Y") || confirm.matches("y"))
                                {
                                    allReserves.add(tempRes);
                                    reservationComplete = true;
                                    display.Show("Thank you! Your reservation has been confirmed with reservation number " + tempRes.getReserveID() + ".", true);
                                    display.Show("We look forward to your stay!", false);
                                }
                            }
                            else
                            {
                                display.Show("Your dates are not available for the chosen room. Please select different dates.", false);
                            }
                        }
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a room before trying to make a reservation.", false);
                }
            }
                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.CANCEL);
                break;
        }
    }
        
    private void checkInMenu() throws DateOutOfRangeException
    {
        txn = new Transaction();
        int menuOption = display.getMenuInput();

        switch(menuOption)
        {
            case 1:
            {
                String resultsStr = "";
                
                for(Reservation res : allReserves)
                {
                    resultsStr += "=========================\n";
                    resultsStr += "Result #" + allReserves.indexOf(res) + ":\n";
                    resultsStr += res.toString();
                    resultsStr += "\n=========================\n";
                }
                
                display.ShowResults(resultsStr);
                
                if(currentUser.getClass() == hotelmanagement.User.class)
                {
                    display.Show("You must be logged in to check in!", true);
                    display.Show("Return to the main menu to login, then search again.", false);
                }                
                else if(allReserves != null && !allReserves.isEmpty())
                {
                    String response = "";
                    Reservation resCheckin = null;
                    
                    while(!(response.matches("Y") || response.matches("y")))
                    {
                        display.Show("Based on your latest search, enter the result # for the reservation.", true);
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < allReserves.size())
                        {
                            display.Show("=========================", true);
                            display.Show(allReserves.get(resultIndex).toString(), true);
                            display.Show("=========================", true);
                            display.Show("Is the above the correct room? Y/N", true);
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                resCheckin = allReserves.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(allReserves.size() - 1) + "]", false);
                        }
                    }
                    
                    display.Show("=========================", true);
                    display.Show("Reservation Details:", true);
                    display.Show(resCheckin.toString(), true);
                    display.Show("=========================", true);
                    display.Show("Confirm reservation? Y/N", true);
                    String confirm = display.getStrInput();

                    if(confirm.matches("Y") || confirm.matches("y"))
                    {
                        txn.CheckIn(resCheckin);
                        resCheckin.setCheckedIn(true);
                        display.Show("Thank you! Your reservation has been checked in with reservation number " + resCheckin.getReserveID() + ".", true);
                        display.Show("Enjoy your stay!", false);
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a reservation before trying to check in.", false);
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
                display.setState(StateEnum.CANCEL);
            }
            break;
        }
    }
    
    private void checkOutMenu() throws ParseException
    {
        int menuOption = display.getMenuInput();

        switch(menuOption)
        {
            case 1:
            {
                display.Show("By which parameter would you like to search?", true);
                display.Show("1. Reservation ID", true);
                display.Show("2. User ID", true);
                display.Show("3. Room Number", true);
                display.Show("4. Reservation Date Range", true);
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
                            display.Show("Input a search parameter, then press Enter. Type # and Enter when finished.", true);
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
                        display.Show("Start Date? (DD/MM/YYYY)", true);
                        String reserveDate1 = display.getStrInput();
                        display.Show("End Date? (DD/MM/YYYY)", true);
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
                    display.Show("No results!", false);
                }
                else
                {
                    String resultsStr = "";
                    for(Reservation res : resResults)
                    {
                        resultsStr += "=========================\n";
                        resultsStr += "Result #" + resResults.indexOf(res) + ":\n";
                        resultsStr += res.toString();
                        resultsStr += "\n=========================\n";
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
                        display.Show("Based on your latest search, enter the result # for the reservation you wish to check-out.", true);
                        int resultIndex = display.getIntInput();
                        
                        if(resultIndex >= 0 && resultIndex < resResults.size())
                        {
                            display.Show("=========================", true);
                            display.Show(resResults.get(resultIndex).toString(), true);
                            display.Show("=========================", true);
                            display.Show("Is the above the correct reservation? Y/N", true);
                            response = display.getStrInput();
                            if(response.matches("Y") || response.matches("y"))
                            {
                                reserveToCheckout = resResults.get(resultIndex);
                            }
                        }
                        else
                        {
                            display.Show("Please enter a valid result number. Current range: [0," + Integer.toString(roomResults.size() - 1) + "]", false);
                        }
                    }
                    
                    boolean checkoutComplete = false;
                    boolean partialPayment = false;
                    while(!checkoutComplete)
                    {
                        display.Show("The remaining bill for this reservation is $" + reserveToCheckout.getCurrentPrice(), true);
                        display.Show("Enter an amount to pay:", true);
                        double payAmt = Double.parseDouble(display.getStrInput());
                        Transaction trans = new Transaction();
                        if(!trans.CheckOut(reserveToCheckout, payAmt))
                        {
                            display.Show("Thank you for partial payment. You still owe $" + reserveToCheckout.getCurrentPrice(), true);
                            display.Show("Payment must be in full to check-out.", true);
                            display.Show("Stop check-out and return to check-out menu?", true);
                            String returnInput = display.getStrInput();
                            if(returnInput.matches("Y") || returnInput.matches("y"))
                            {
                                display.Show("Current reservation check-out incomplete! Returning to check-out menu...", false);
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
                        display.Show("Check-out complete for reservation #" + reserveToCheckout.getReserveID(), false);
                    }
                    else
                    {
                        display.Show("Check-out incomplete for reservation #" + reserveToCheckout.getReserveID(), false);
                    }
                }
                else
                {
                    display.Show("There are no current search results! Please search for a reservation before trying to make a check-out.", false);
                }
            }
                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.CANCEL);
                break;
        }
    }
    
    private void cancelMenu()
    {
        int menuOption = display.getMenuInput();

        switch(menuOption)
        {
            case 1:
            {
                if(currentUser.getClass() == Customer.class)
                {
                    display.setState(StateEnum.CUSTOMER);
                }
                else if (currentUser.getClass() == Employee.class)
                {
                    display.setState(StateEnum.EMPLOYEE);
                }
                else
                {
                    display.setState(StateEnum.MAIN);
                }
            }
                break;
            case 2:
                display.setState(StateEnum.SEARCH);
                break;
            case 3:
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
