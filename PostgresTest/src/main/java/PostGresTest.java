import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostGresTest {

	public static void main(String[] args) {
			
        try {
        	
        	String jdbcUrl = "jdbc:postgresql://192.168.1.12:5432";
            String user = "postgres";
            String password = "root";
            String dbname = "postgres";
        	Connection conn = DriverManager.getConnection(jdbcUrl+"/"+dbname, user, password);
            System.out.println("Conexión realizada.");
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE coches (matricula varchar(8), marca varchar(32), color varchar(16), fecha int);");
            st.execute("INSERT INTO coches VALUES ('1111-ABC','AUDI QUATRO', 'AZUL', 2001)");
            st.execute("INSERT INTO coches VALUES ('2222-ABC','MERCEDES C220', 'NEGRO', 2002)");
            st.execute("INSERT INTO coches VALUES ('3333-ABC','SKODA OCTAVIA', 'ROJO', 2002)");
            ResultSet rs = st.executeQuery("SELECT * FROM coches");
            while (rs.next()) {
	            String snombre = rs.getString("matricula");
	            String smarca = rs.getString("marca");
	            String scolor = rs.getString("color");
	            int ifecha = rs.getInt("fecha");
	            System.out.println(snombre + ", " + smarca+ ", " + scolor+ ", " + ifecha);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
        	System.out.println("ERROR DE CONEXIÓN:");
            System.err.println(e.getMessage());
        }
	}

}
