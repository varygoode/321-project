/*
 *
 */
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class UserFactory 
{
    static private UserFactory singletonFactory = new UserFactory();
    private User user = null;
    
    private UserFactory()
    {
        System.out.print("Construction not allowed.");
    }
    
    public static UserFactory getUserFactory()
    {
        return singletonFactory;
    }
            
    public User getAUser()
    {
        if (this.user == null)
        {
            synchronized(this)
            {
                if(this.user == null)
                {
                    if (this.user.getClass().equals(hotelmanagement.User.Customer.class))
                    {
                        this.user = new Customer();
                    }
                    else if (this.user.getClass().equals(hotelmanagement.User.Employee.class))
                    {
                        this.user = new Employee();
                    }
                }
            }
        }    
        return this.user;
    } 
}
