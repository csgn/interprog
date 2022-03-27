package DAO;

import Model.CustomerType;
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

public class CustomerTypeDAO implements IDAO<CustomerType>{

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private CustomerType tmp;
	private List<CustomerType> customerTypes;
	
	
	public CustomerType find(int id){
		CustomerType customerType = new CustomerType();
		try{
			this.ps = con.prepareStatement("SELECT * FROM customertype WHERE id=?");
			this.ps.setInt(1,id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				customerType.setId(rs.getInt("id"));
				customerType.setName(rs.getString("name"));
			}
		}catch(SQLException e){
			Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return customerType;
	}

	public List<CustomerType> findAll() {

		try {
			customerTypes = new ArrayList<>();
			this.ps = this.con.prepareStatement("Select * from customertype");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CustomerType(
								rs.getInt("id"),
								rs.getString("name"));
				customerTypes.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customerTypes;
	}

	public CustomerType findById(int id) {
		try {
			this.ps = this.con.prepareStatement("Select * from customertype where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CustomerType(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public CustomerType findByName(String name) {
		try {
			this.ps = this.con.prepareStatement("Select * from customertype where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CustomerType(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public int create(CustomerType a) {
		int id = -1;
		try {
			ps = con.prepareStatement("INSERT INTO customertype (name) VALUES (?) RETURNING id");
			ps.setString(1, a.getName());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}
	
	public void update(CustomerType a) {

		try {
			ps = con.prepareStatement("UPDATE customertype set name=? where id=?");
			ps.setString(1, a.getName());
			ps.setInt(2, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void delete(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM customertype where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerType.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	public void deleteByName(String name){
		try {
			this.ps = this.con.prepareStatement("delete from customertype where name = ?");
			this.ps.setString(1, name);
			ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
