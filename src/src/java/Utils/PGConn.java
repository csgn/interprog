package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csgn (Sergen Cepoglu)
 */
public class PGConn {

	private static PGConn instance;
	private Connection conn;
	final private static String DB_URL = "jdbc:postgresql://localhost:5432/interprog";
	final private static String DB_USERNAME = "postgres";
	final private static String DB_PASSWORD = "12345";

	public static PGConn connect() {
		if (PGConn.instance == null) {
			PGConn.instance = new PGConn();
		}
		
		return PGConn.instance;
	}

	public Connection getConnection() {
		try {
			if (this.conn == null || this.conn.isClosed()) {
				try {
					Class.forName("org.postgresql.Driver");
					this.conn = DriverManager.getConnection(PGConn.DB_URL, PGConn.DB_USERNAME, PGConn.DB_PASSWORD);
					System.out.println("Connection Done");
					
				} catch (SQLException e) {
					System.out.println("ERROR: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("ERROR: " + e.getMessage());
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(PGConn.class.getName()).log(Level.SEVERE, null, ex);
		}

		return this.conn;
	}

}