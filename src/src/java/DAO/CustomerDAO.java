package DAO;

import Model.Customer;
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
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public int create(Customer t) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void update(Customer t) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void delete(int id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}