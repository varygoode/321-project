
package hotelmanagement;

/**
 *
 * @author timothy
 */
public class UserFactory 
{
    static private UserFactory singletonFactory;
    
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
            
    public User createUser(Class<?> classArg, String username, String password, String fName, String lName, int ID)
    {
        if (classArg.equals(hotelmanagement.Customer.class))
        {
            return new Customer(username, password, fName, lName, ID);
        }
        else if (classArg.equals(hotelmanagement.Employee.class))
        {
            return new Employee(username, password, fName, lName, ID);
        }
        return new User(username, password, fName, lName, ID);
    } 
    public void getMessage()
    {
        System.out.print("You made a user");
    }
}
