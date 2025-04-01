
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    public static Connection connect() throws SQLException {
        try {
            String jdbcUrl = DatabaseConfig.getDbUrl();
            String user = DatabaseConfig.getDbUsername();
            String password = DatabaseConfig.getDbPassword();
            String dbname = DatabaseConfig.getDbName();
            // Open a connection
            return DriverManager.getConnection(jdbcUrl+"/"+dbname, user, password);
        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
