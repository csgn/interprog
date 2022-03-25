package DAO;

import Model.AccountType;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aykut
 */

public class AccountTypeDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private AccountType tmp;
	private List<AccountType> accountTypes;

	public List<AccountType> findAll() {

		try {
			accountTypes = new ArrayList<>();
			this.ps = this.con.prepareStatement("Select * from accounttype");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new AccountType(
								rs.getInt("id"),
								rs.getString("name"));
				accountTypes.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return accountTypes;
	}

	public AccountType findById(int id) {
		try {
			this.ps = this.con.prepareStatement("Select * from accounttype where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new AccountType(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public AccountType findByName(String name) {
		try {
			this.ps = this.con.prepareStatement("Select * from accounttype where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new AccountType(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public void insert(int id, String name) {
		try {
			this.ps = this.con.prepareStatement("insert into accounttype values (id = ? ,name = ?)");
			this.ps.setInt(1, id);
			this.ps.setString(2,name);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteById(int id){
		try {
			this.ps = this.con.prepareStatement("delete from accounttype where id = ?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByName(String name){
		try {
			this.ps = this.con.prepareStatement("delete from accounttype where name = ?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
