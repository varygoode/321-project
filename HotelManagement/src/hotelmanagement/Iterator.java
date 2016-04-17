
package hotelmanagement;
import java.util.NoSuchElementException;
/**
 *
 * @author hzhang
 */
public interface Iterator {
    public Object next()throws NoSuchElementException;
    public boolean hasNext();
    
}
