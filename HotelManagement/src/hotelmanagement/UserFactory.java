
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class UserFactory 
{
    static private UserFactory singletonFactory;
    private User user;
    
    private UserFactory()
    {

    }
    
    public static UserFactory getUserFactory()
    {
        if (singletonFactory == null)
        {
            singletonFactory = new UserFactory();
        } 
        return singletonFactory;
    }
            
    public User getAUser()
    {
        if (user.getClass().equals(hotelmanagement.Customer.class))
        {
            user = new Customer();
        }
        else if (user.getClass().equals(hotelmanagement.Employee.class))
        {
            user = new Employee();
        }
        else
        {
            user = new User();
        }
        return this.user;
    } 
    public void getMessage(){
    
        System.out.print("You made a user");
    }
}
