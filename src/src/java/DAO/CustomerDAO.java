package DAO;

import Model.Customer;
import Utils.PGConn;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerDAO implements IDAO<Customer>{

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Customer tmp;
	private List<Customer> customers;
	
	public List<Customer> findAll() {

		customers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from customer");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
				customers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	public Customer findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from customer where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Customer findByPhone(String phone) {
		try {
			this.ps = this.con.prepareStatement("Select * from customer where phone =?");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Customer findByEmail(String email) {

		try {
			this.ps = this.con.prepareStatement("Select * from customer where email =?");
			this.ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Customer findByAddressId(int addressId) {
		try {
			this.ps = this.con.prepareStatement("Select * from customer where addressId =?");
			this.ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<Customer> findByAccountType(int accountTypeId) {

		customers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from customer where accountTypeId =?");
			this.ps.setInt(1, accountTypeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"));
				customers.add(tmp);
			}
			System.out.println(customers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	public void insert(int id, String phone, String email, int addressId, int accountTypeId) {
		try {
			this.ps = this.con.prepareStatement("insert into customer values (id = ? ,phone = ?, email = ?, addressId = ?, accountTypeId = ? )");
			this.ps.setInt(1, id);
			this.ps.setString(2, phone);
			this.ps.setString(3, email);
			this.ps.setInt(4, addressId);
			this.ps.setInt(5, accountTypeId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteById(int id) {
		try {
			this.ps = this.con.prepareStatement("delete from customer where (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByEmail(String email){
		try {
			this.ps = this.con.prepareStatement("delete from customer where (email = ?)");
			this.ps.setString(1, email);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByPhone(String phone){
		try {
			this.ps = this.con.prepareStatement("delete from customer where (phone = ?)");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Customer find(int id) {
		Customer customer = new Customer();
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setPhone(rs.getString("phone"));
				customer.setEmail(rs.getString("email"));
				customer.setAddressId(rs.getInt("addressId"));
				customer.setAccountTypeId(rs.getInt("accountTypeId"));
			}
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return customer;
		
	}

	@Override
	public int create(Customer c) {
		int id = -1;

		try {
			ps = con.prepareStatement("INSERT INTO customer (phone, email, addressid, accounttypeid) VALUES (?, ?, ?, ?) RETURNING id");
			ps.setString(1, c.getPhone());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getAddressId());
			ps.setInt(4, c.getAccountTypeId());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	@Override
	public void update(Customer c) {
		try {
			ps = con.prepareStatement("UPDATE customer SET phone=?,email=?,addressId=?,accounttypeid=?,where id=?");
			ps.setString(1, c.getPhone());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getAddressId());
			ps.setInt(4, c.getAccountTypeId());
			ps.setInt(5, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM customer where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}