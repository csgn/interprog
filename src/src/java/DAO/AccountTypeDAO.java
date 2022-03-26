package DAO;

import Model.AccountType;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aykut
 */

public class AccountTypeDAO implements IDAO<AccountType>{

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private AccountType tmp;
	private List<AccountType> accountTypes;
	
	
	public AccountType find(int id){
		AccountType accountType = new AccountType();
		try{
			this.ps = con.prepareStatement("SELECT * FROM accounttype WHERE id=?");
			this.ps.setInt(1,id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				accountType.setId(rs.getInt("id"));
				accountType.setName(rs.getString("name"));
			}
		}catch(SQLException e){
			Logger.getLogger(AccountTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return accountType;
	}

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

	public int create(AccountType a) {
		int id = -1;
		try {
			ps = con.prepareStatement("INSERT INTO accounttype (name) VALUES (?) RETURNING id");
			ps.setString(1, a.getName());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(AccountTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}
	
	public void update(AccountType a) {

		try {
			ps = con.prepareStatement("UPDATE accounttype set name=? where id=?");
			ps.setString(1, a.getName());
			ps.setInt(2, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(AccountTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void delete(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM accounttype where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(AccountType.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	public void deleteByName(String name){
		try {
			this.ps = this.con.prepareStatement("delete from accounttype where name = ?");
			this.ps.setString(1, name);
			ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
