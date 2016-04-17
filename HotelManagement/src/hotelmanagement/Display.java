/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagement;


import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import newExceptions.DateOutOfRangeException;

/**
 * @author andreapressnell
 */
public class Display
{
    private StateEnum state;
    int menuOption;
    JPanel menuPanel;
    JFrame frame;
    JButton login, register, search, quit, searchOrRes, cancelRes;
    JButton returnToMain, cancel, alterRoom, checkIn, checkOut;
    JButton report, searchRooms, makeRes, findRes, completeCheckout;
    JButton submit, enter;
    JTextField input;
    boolean submitClicked;
    HotelManagement hms;
    String prompt;
    int updateCt = 0;
       
    public Display(HotelManagement hms)
    {
        //constructor 
        state = StateEnum.MAIN;
        submitClicked = false;
        this.hms = hms;
        prompt = "";
        
        menuPanel = new JPanel();
        frame = new JFrame();
         
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        ////////////SET UP BUTTONS///////////////
        login = new JButton("Login");
        register = new JButton("Register");
        search = new JButton("Search");
        quit = new JButton("Quit");
        searchOrRes = new JButton("Search/Reserve");
        cancelRes = new JButton("Cancel Reservation");
        returnToMain = new JButton("Main Menu");
        cancel = new JButton("Cancel Menu");
        alterRoom = new JButton("Alter Room");
        checkIn = new JButton("Check-In");
        checkOut = new JButton("Check-Out");
        report = new JButton("Report");
        searchRooms = new JButton("Search Rooms");
        makeRes = new JButton("Make Reservation");
        findRes = new JButton("Find Reservation");
        completeCheckout = new JButton("Complete Checkout");
        enter = new JButton("Enter");
        submit = new JButton("Submit");
        ////////END SET UP BUTTONS///////////////
        
        /////INPUT BOX/////
        input = new JTextField(32);
        
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        frame.getContentPane().setBackground(Color.red);
        frame.add(menuPanel, BorderLayout.NORTH);
        
        //frame.pack();
        frame.setVisible(true);
    }
    
    private void startMenu()
    {            
        menuOption = 0;
        removeAllActionListeners(login);
        removeAllActionListeners(register);
        removeAllActionListeners(search);
        removeAllActionListeners(quit);
        menuPanel.removeAll();
        menuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(new JLabel("**MAIN MENU**"));
        menuPanel.add(new JLabel("============="));
        menuPanel.add(login); //returning user
        menuPanel.add(register); //creates new employee or customer
        menuPanel.add(search); //defaults to customer Menu
        menuPanel.add(quit); //endProgram
        login.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                   System.out.println("Count of listeners: " + login.getActionListeners().length);
                   menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        register.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        search.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        quit.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 4;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
}
    
    private void custMenu()
    {
        menuOption = 0;
        removeAllActionListeners(searchOrRes);
        removeAllActionListeners(cancelRes);
        removeAllActionListeners(returnToMain);
        removeAllActionListeners(cancel);
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**CUSTOMER MENU**"));
        menuPanel.add(new JLabel("================="));
        menuPanel.add(searchOrRes); //takes you to search menu
        menuPanel.add(cancelRes);
        menuPanel.add(returnToMain);
        menuPanel.add(cancel);
        searchOrRes.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancelRes.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancel.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 4;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void empMenu()
    {
        menuOption = 0;
        removeAllActionListeners(search);
        removeAllActionListeners(alterRoom);
        removeAllActionListeners(checkIn);
        removeAllActionListeners(checkOut);
        removeAllActionListeners(report);
        removeAllActionListeners(returnToMain);
        removeAllActionListeners(cancel);
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**EMPLOYEE MENU**"));
        menuPanel.add(new JLabel("================="));
        menuPanel.add(search);
        menuPanel.add(alterRoom); 
        menuPanel.add(checkIn); 
        menuPanel.add(checkOut);
        menuPanel.add(report);
        menuPanel.add(returnToMain);
        menuPanel.add(cancel);
        search.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        alterRoom.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        checkIn.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        checkOut.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 4;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        report.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 5;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 6;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancel.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 7;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void searchMenu()
    {
        menuOption = 0;
        removeAllActionListeners(searchRooms);
        removeAllActionListeners(makeRes);
        removeAllActionListeners(returnToMain);
        removeAllActionListeners(cancel);
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**SEARCH**"));
        menuPanel.add(new JLabel("=========="));
        menuPanel.add(searchRooms); //filters to room data will be applied 
        menuPanel.add(makeRes); //takes you to Reservation Menu
        menuPanel.add(returnToMain);
        menuPanel.add(cancel);
        searchRooms.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        makeRes.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancel.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 4;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void checkInMenu()
    {
        menuOption = 0;
        findRes.removeAll();
        returnToMain.removeAll();
        cancel.removeAll();
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**Check-In Menu**"));
        menuPanel.add(new JLabel("================="));
        menuPanel.add(findRes);
        menuPanel.add(returnToMain);
        menuPanel.add(cancel);
        findRes.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancel.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void checkOutMenu()
    {
        menuOption = 0;
        removeAllActionListeners(findRes);
        removeAllActionListeners(completeCheckout);
        removeAllActionListeners(returnToMain);
        removeAllActionListeners(cancel);
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**Check-Out Menu**"));
        menuPanel.add(new JLabel("=================="));
        menuPanel.add(findRes);
        menuPanel.add(completeCheckout);
        menuPanel.add(returnToMain);
        menuPanel.add(cancel);
        findRes.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        completeCheckout.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        cancel.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 4;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void cancelMenu()
    {
        menuOption = 0;
        removeAllActionListeners(returnToMain);
        removeAllActionListeners(search);
        removeAllActionListeners(quit);
        menuPanel.removeAll();
        menuPanel.add(new JLabel("**Cancel**"));
        menuPanel.add(new JLabel("=========="));
        menuPanel.add(returnToMain);
        menuPanel.add(search);
        menuPanel.add(quit);
        returnToMain.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 1;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        search.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 2;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        quit.addActionListener(new 
            ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                  menuOption = 3;
                   try {
                       hms.run();
                   } catch (ParseException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (DateOutOfRangeException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (SQLException ex) {
                       Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
            });
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
    
    private void quitMenu()
    {
        menuOption = 0;
        menuPanel.removeAll();
        menuPanel.add(new JLabel("============="));
        menuPanel.add(new JLabel("**Good Bye!**"));
        menuPanel.add(new JLabel("============="));
        menuPanel.revalidate();
        menuPanel.repaint();
        frame.revalidate();
        frame.repaint();
        try 
        {
            TimeUnit.MILLISECONDS.sleep(30);
            try {
                hms.run();
            } catch (ParseException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DateOutOfRangeException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Updates the display to show appropriate menu or prompt.
     */
    public void update()
    {
        System.out.println("UPDATE #" + Integer.toString(++updateCt));
        System.out.println(state.toString());
        System.out.println(menuOption);
        switch(state)
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
                quitMenu();
                break;
            default:
                startMenu();
                break;
        }
    }
    
    public void setState(StateEnum state)
    {
        this.state = state;
    }
    
    public StateEnum getState()
    {
        return this.state;
    }
    
    /**
     * Public function to get integer input from display.
     * @return input as an integer
     */
    public int getIntInput()
    {
        return Integer.valueOf(getStrInput());
    }
    
    /**
     * Public function to get menu input number based on button.
     * @return input as an integer
     */
    public int getMenuInput()
    {
        return menuOption;
    }
    
    /**
     * Public function to get string input from display.
     * @return input as a string
     */
    public String getStrInput()
    {
        String input = JOptionPane.showInputDialog(prompt);
        prompt = "";
        return input;
    }
    
    public double getDoubleInput()
    {
        return Double.valueOf(getStrInput());
    }
    
    public void Show(String str, boolean isInputPrompt)
    {
        if(!prompt.isEmpty())
        {
            prompt += "\n";
        }
        prompt += str;
        if(!isInputPrompt)
        {
            JOptionPane.showMessageDialog(null, prompt);
            prompt = "";
        }
    }
    
    public void ShowResults(String str)
    {
        JDialog dialog = new JDialog(frame, false);
        
        JScrollPane resultsScrollPanel = new JScrollPane(new JTextArea(str)){
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(480, 320);
                }
        };
        
        dialog.add(resultsScrollPanel);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(frame.getWidth(), frame.getHeight());
        dialog.setLocationRelativeTo(frame);
        dialog.setLocation(frame.getWidth(), 0);
        
        dialog.setVisible(true);
    }
    
    private void removeAllActionListeners(JButton button)
    {
        for(ActionListener act : button.getActionListeners()) 
        {
            button.removeActionListener(act);
        }
    }
}//end display
