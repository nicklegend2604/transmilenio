package ejemplo_mvc_tisc.repository;

import ejemplo_mvc_tisc.models.Tisc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas.espitia
 */
public class TiscArrayImpl implements ITiscRepository{
    
    private List<Tisc> cards;

    public TiscArrayImpl() {
        cards = new ArrayList<>();
    }

    @Override
    public void save(Tisc tisc) {
        cards.add(tisc);
    }

    @Override
    public List<Tisc> getAll() {
        return cards;
    }
    
}
