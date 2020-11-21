
package ejemplo_mvc_tisc.repository;

import ejemplo_mvc_tisc.models.Tisc;
import java.util.List;

/**
 *
 * @author nicolas.espitia
 */
public interface ITiscRepository {
    
    public void save(Tisc tisc);
    
    public List<Tisc> getAll();
    
}
