/*
 * 
 */
package hotelmanagement;

import java.util.ArrayList;
import java.util.Date;


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
    
    public void run()
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
                }
            }
                break;
            case 3:
                display.setState(StateEnum.SEARCH);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;

        }            
    }
    
    private void custMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:
                display.setState(StateEnum.SEARCH);

                break;
            case 2:
                display.setState(StateEnum.MAIN);
                break;
            case 3:
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
                display.setState(StateEnum.MAIN);
                break;
            case 8:
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
                display.setState(StateEnum.MAIN);
                break;
            case 4:
                display.setState(StateEnum.QUIT);
                break;
        }
    }
    
    private void reservationMenu()
    {
        int menuOption = display.getIntInput();

        switch(menuOption)
        {
            case 1:

                break;
            case 2:

                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
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
            {
                
            }
            break;
            case 2:

                break;
            case 3:
                display.setState(StateEnum.MAIN);
                break;
            case 4:
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
}
