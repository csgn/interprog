package Controller;

import Utils.PGConn;
import Utils.PGConn.PGRow;
import java.sql.Connection;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;


@Named("appBean")
@SessionScoped
public class AppBean implements Serializable {
	private String test;

	public AppBean() {
	}
	
	public void setTest(String test) {
		this.test = test;
		Connection conn = PGConn.getConnection();
		ArrayList<PGConn.PGRow> rows = PGConn.makeQuery("Select * from customer");

		System.out.println(rows.toString());

	}


}
