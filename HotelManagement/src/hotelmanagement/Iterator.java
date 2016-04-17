
package hotelmanagement;
import java.util.NoSuchElementException;
/**
 *
 * @author James
 */
public interface Iterator {
    public Object next()throws NoSuchElementException;
    public boolean hasNext();
    
}
