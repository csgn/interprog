package DAO;

import Model.CustomerType;
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

	private CustomerType tmp;
	private List<CustomerType> customerTypes;
	
	@Override
	public CustomerType find(int id){
		PreparedStatement ps;
		ResultSet rs;
		CustomerType customerType = null;
		try{
			ps = conn.prepareStatement("SELECT * FROM customertype WHERE id=?");
			ps.setInt(1,id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				customerType = new CustomerType(rs.getInt("id"), rs.getString("name"));
			}
		}catch(SQLException e){
			Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return customerType;
	}

	@Override
	public List<CustomerType> findAll() {
		PreparedStatement ps;
		ResultSet rs;
		try {
			customerTypes = new ArrayList<>();
			ps = conn.prepareStatement("Select * from customertype");
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
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("Select * from customertype where id =?");
			ps.setInt(1, id);
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
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("Select * from customertype where name =?");
			ps.setString(1, name);
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

	@Override
	public int create(CustomerType a) {
		PreparedStatement ps;
		ResultSet rs;
		int id = -1;
		try {
			ps = conn.prepareStatement("INSERT INTO customertype (name) VALUES (?) RETURNING id");
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
	
	@Override
	public void update(CustomerType a) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE customertype set name=? where id=?");
			ps.setString(1, a.getName());
			ps.setInt(2, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	@Override
	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM customertype where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerType.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	public void deleteByName(String name){
		PreparedStatement ps;
		try {
			ps = this.conn.prepareStatement("delete from customertype where name = ?");
			ps.setString(1, name);
			ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
