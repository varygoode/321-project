/*
 * 
 */
package hotelmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;


public class HotelManagement
{
    static private HotelManagement singletonHMS = null;
    
    ArrayList<Room> allRooms;
    ArrayList<User> allUsers;
    ArrayList<Reservation> allReserves;
    Ledger theLedger;
    Display display;
    User currentUser;
    
    
//    public HotelManagement() 
//    {
//        allRooms = new ArrayList();
//        allUsers = new ArrayList();
//        allReserves = new ArrayList();
//        theLedger = Ledger.getLedger();
//        display = new Display();
//    }
    private HotelManagement()
    {
        allRooms = new ArrayList();
        allUsers = new ArrayList();
        allReserves = new ArrayList();
        theLedger = Ledger.getLedger();
        display = new Display();
        currentUser = new User();
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
        //  Factories
        //===============================
        //obtain the factory - Room
        RoomFactory roomFactory = RoomFactory.getRoomFactory();

        //obtain the factory - User
        UserFactory userFactory = UserFactory.getUserFactory();

        //obtain the factory - Reservation
        ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();

        //===============================
        //  Initialize Users
        //===============================
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user1","pass1","Andrew","Jackson",10001));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user2","pass2","Martha","Washington",10002));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user3","pass3","Harold","Truman",10003));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user4","pass4","Barbara","Bush",10004));
        allUsers.add(userFactory.createUser(hotelmanagement.Customer.class,"user5","pass5","Millard","Fillmore",10005));

        //===============================
        //  Initialize Rooms
        //===============================
        for (int i = 1; i<=3; i++)
        {
            for (int j = 0; j<=10; j++)
            {
                allRooms.add(roomFactory.createRoom("Double Queen", (i*100)+j, "Two beautiful queen beds situated in a glorious simulated room. Perfect for divorced couples.", 89));
            }
        }

        //===============================
        //  Initialize Reservations
        //===============================
        allReserves.add(reservationFactory.createReservation(new Date(116,1,1), new Date(116,1,5), allRooms.get(5), true, allUsers.get(0), 1000000));
        allReserves.add(reservationFactory.createReservation(new Date(116,2,1), new Date(116,2,4), allRooms.get(10), true, allUsers.get(1), 1000001));
        allReserves.add(reservationFactory.createReservation(new Date(116,3,1), new Date(116,3,6), allRooms.get(15), true, allUsers.get(2), 1000002));
        allReserves.add(reservationFactory.createReservation(new Date(116,4,1), new Date(116,4,3), allRooms.get(20), true, allUsers.get(3), 1000003));
        allReserves.add(reservationFactory.createReservation(new Date(116,5,12), new Date(116,5,25), allRooms.get(25), true, allUsers.get(4), 1000004));
    }
    
    public void run() throws ParseException
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
                case RESERVATION:
                    reservationMenu();
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
                    endProgram=true;
                    break;
                default:
                    startMenu();
                    break;
            }
            
            display.update();
        }
        System.exit(0);

    }
    
    public Customer registerCustomer(String username, String password, String fName, String lName, int ID)
    {
        return new Customer(username, password, fName, lName, ID);
    }
    
    public Employee registerEmployee(String username, String password, String fName, String lName, int ID)
    {
        return new Employee(username, password, fName, lName, ID);
    }
    
    public User login(String username, String password)
    {
        ArrayList<String> params = new ArrayList<String>();
        params.add(username);
        ArrayList<User> results = theLedger.search(allUsers, params);
        User tempUser = (results.isEmpty()) ? currentUser : results.get(0);
        
        display.Show("Enter your username:");
        String inUsername = display.getStrInput();
        display.Show("Enter your password:");
        String inPassword = display.getStrInput();
        
        if(tempUser.getUsername() == inUsername && tempUser.getPassword() == inPassword)
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
                //User user = hms.allUsers.get(0);
                //user.Login(ArrayList<User> userList, String name, String password);
                //access to login needs work
                break;
            case 2:
                display.setState(StateEnum.RESERVATION);
                break;
            case 3:
                display.setState(StateEnum.SEARCH);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;

        }            
    }//end public function
    
    private void custMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
                display.setState(StateEnum.SEARCH);

                break;
            case 2:
                display.setState(StateEnum.QUIT);
                break;

        }
    }
    
    private void empMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
                display.setState(StateEnum.SEARCH);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:
                display.setState(StateEnum.QUIT);
                break;

        }
    }
    
    private void searchMenu()
    {
        int menuOption = display.getIntInput();
            
        switch(menuOption)
        {
            case 1:

                break;
            case 2:

                break;
            case 3:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private void reservationMenu() throws ParseException
    {
        ReservationFactory reservationFactory = ReservationFactory.getReservationFactory();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
                display.Show("When would you like to reserve the room from? (dd/MM/yyyy)");
                String reserveDate1 = display.getStrInput();
                display.Show("When would you like to reserve the room until? (dd/MM/yyyy)");
                String reserveDate2 = display.getStrInput();
                Date d1 = df.parse(reserveDate1);
                Date d2 = df.parse(reserveDate2);
                Reservation newRes = reservationFactory.createReservation(d1, d2, allRooms.get(5), true, allUsers.get(0), 1000000);
                allReserves.add(newRes);
                break;
            case 2:

                break;
            case 3:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private void checkInMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:

                break;
            case 2:

                break;
            case 3:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private void checkOutMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:

                break;
            case 2:

                break;
            case 3:
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
                display.setState(StateEnum.QUIT);
                break;
        }
    }
}
