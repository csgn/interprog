package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;

import org.postgresql.jdbc.PgResultSetMetaData;

/**
 *
 * @author csgn (Sergen Cepoglu)
 */
public abstract class PGConn {

	public static class PGRow extends HashMap<String, Object> {
	}

	final private static String DB_URL = "jdbc:postgresql://localhost:5432/interprog";
	final private static String DB_USERNAME = "postgres";
	final private static String DB_PASSWORD = "12345";

	public static Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(PGConn.DB_URL, PGConn.DB_USERNAME, PGConn.DB_PASSWORD);
			System.out.println("Connection Done");

		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return conn;
	}

	public static ArrayList<PGConn.PGRow> makeQuery(String queryString) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = PGConn.getConnection();
		ArrayList<PGConn.PGRow> rows = new ArrayList<PGConn.PGRow>();

		try {
			pst = conn.prepareStatement(queryString);
			pst.execute();
			rs = pst.getResultSet();

			PgResultSetMetaData meta = null;
			meta = (PgResultSetMetaData) rs.getMetaData();

			ArrayList<String> cols = new ArrayList<>();

			for (int i = 1; i <= meta.getColumnCount(); i++) {
				cols.add(meta.getColumnName(i));
			}

			while (rs.next()) {
				PGConn.PGRow row = new PGConn.PGRow();

				for (String col : cols) {
					Object r = rs.getObject(col);
					row.put(col, r);
				}

				rows.add(row);
			}

			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return rows;
	}

}
