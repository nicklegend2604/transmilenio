
package ejemplo_mvc_tisc.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicolas.espitia
 */
public class Config {
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/transmilenio;user=nicolas;password=123";
    private static final Logger logger = Logger.getLogger(Config.class.getName());
    
    private Config(){}
    
    public static Connection getConnection(){
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            return DriverManager.getConnection(DB_URL);         
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException except){
            logger.log(Level.SEVERE, "Cannot connect to DB", except);
            return null;
        }
    }
    
}
