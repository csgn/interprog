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
public class CustomerDAO implements IDAO<Customer> {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Customer tmp;
	private List<Customer> customers;

	@Override
	public Customer find(int id) {
		Customer customer = new Customer();

		try {
			ps = con.prepareStatement("SELECT * FROM customer WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setSurname(rs.getString("surname"));
				customer.setCustomerTypeId(rs.getInt("customerTypeId"));
				customer.setPhone(rs.getString("phone"));
				customer.setCompanyTitle(rs.getString("companyTitle"));
				customer.setTaxNumber(rs.getString("taxNumber"));
				customer.setTaxAdministration(rs.getString("taxAdministration"));
				customer.setAddressId(rs.getInt("addressId"));
			}
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return customer;
	}

	public List<Customer> findAll() {

		customers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from customer");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getInt("customerTypeId"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								rs.getInt("addressId"));
				customers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	public Customer findByPhone(String phone) {
		try {
			this.ps = this.con.prepareStatement("Select * from customer where phone =?");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getInt("customerTypeId"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								rs.getInt("addressId"));
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
								rs.getString("name"),
								rs.getString("surname"),
								rs.getInt("customerTypeId"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								rs.getInt("addressId"));
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
								rs.getString("name"),
								rs.getString("surname"),
								rs.getInt("customerTypeId"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								rs.getInt("addressId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<Customer> findByCustomerType(int customerTypeId) {
		customers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from customer where customerTypeId =?");
			this.ps.setInt(1, customerTypeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getInt("customerTypeId"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								rs.getInt("addressId"));
				customers.add(tmp);
			}
			System.out.println(customers);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	@Override
	public int create(Customer c) {
		int id = -1;

		try {
			ps = con.prepareStatement("INSERT INTO customer (name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, c.getName());
			ps.setString(2, c.getSurname());
			ps.setInt(3, c.getCustomerTypeId());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getCompanyTitle());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());
			ps.setInt(9, c.getAddressId());
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
			ps = con.prepareStatement("UPDATE customer SET name=?,surname=?,customertypeid=?,phone=?,email=?,companytitle=?,taxnumber=?,taxadministration=?,addressid=? where id=?");
			ps.setString(1, c.getName());
			ps.setString(2, c.getSurname());
			ps.setInt(3, c.getCustomerTypeId());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getCompanyTitle());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());
			ps.setInt(9, c.getAddressId());
			ps.setInt(10, c.getId());
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
