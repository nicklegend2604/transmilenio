package ejemplo_mvc_tisc.repository;

import ejemplo_mvc_tisc.models.Tisc;
import ejemplo_mvc_tisc.persistence.Config;
import ejemplo_mvc_tisc.factory.TiscFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicolas.espitia
 */
public class TiscBDImpl implements ITiscRepository {

    private static final String SQL_INSERT = "INSERT INTO TISC VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_ALL= "SELECT * FROM TISC";
    


    @Override
    public void save(Tisc tisc) {
        Connection conn = Config.getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(SQL_INSERT);
            stm.setString(1, tisc.getSerial());
            stm.setString(2, tisc.getType());
            stm.setDate(3, Date.valueOf(tisc.getExpeditionDate()));
            stm.setInt(4, tisc.getBalance());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TiscBDImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiscBDImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<Tisc> getAll() {
        Connection conn = Config.getConnection();
        List<Tisc> cards = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(SQL_GET_ALL);
            while(rs.next()) {
                Tisc tisc = TiscFactory.createTisc(
                        rs.getString("serial"),
                        rs.getString("type"),
                        rs.getDate("expedition_date").toLocalDate(),
                        rs.getInt("balance"));
                cards.add(tisc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TiscBDImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(TiscBDImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cards;
    }

}
