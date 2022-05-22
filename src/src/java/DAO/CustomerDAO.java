package DAO;

import Model.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aykut
 * @author Sergen
 */
public class CustomerDAO implements IDAO<Customer> {

	private Customer tmp;
	private List<Customer> customers;
	private CustomerTypeDAO customerTypeDAO;
	private AddressDAO addressDAO;

	@Override
	public Customer find(int id) {
		Customer customer = new Customer();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM customer WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setSurname(rs.getString("surname"));
				customer.setCustomerType(this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")));
				customer.setPhone(rs.getString("phone"));
				customer.setCompanyTitle(rs.getString("companyTitle"));
				customer.setTaxNumber(rs.getString("taxNumber"));
				customer.setTaxAdministration(rs.getString("taxAdministration"));
				customer.setAddress(this.getAddressDAO().find(rs.getInt("addressId")));
			}
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return customer;
	}

	@Override
	public List<Customer> findAll() {
		PreparedStatement ps;
		ResultSet rs;

		customers = new ArrayList<>();
		try {
			ps = conn.prepareStatement("Select * from customer");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
				customers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return customers;
	}
	
	@Override
	public List<Customer> findAll(int page, int pageSize) {
		PreparedStatement ps;
		ResultSet rs;

		int start = (page-1)*pageSize;
		
		customers = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM customer limit ? offset ?");
			ps.setInt(1, pageSize);
			ps.setInt(2, start);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
				customers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return customers;
	}

	public Customer findByPhone(String phone) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("Select * from customer where phone =?");
			ps.setString(1, phone);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Customer findByEmail(String email) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("Select * from customer where email =?");
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Customer findByAddressId(int addressId) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("Select * from customer where addressId =?");
			ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<Customer> findByCustomerType(int customerTypeId) {
		PreparedStatement ps;
		ResultSet rs;
		customers = new ArrayList<>();
		try {
			ps = conn.prepareStatement("Select * from customer where customerTypeId =?");
			ps.setInt(1, customerTypeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Customer(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								this.getCustomerTypeDAO().find(rs.getInt("customerTypeId")),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getString("companyTitle"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"),
								this.getAddressDAO().find(rs.getInt("addressId")));
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
		PreparedStatement ps;
		ResultSet rs;
		int id = -1;

		try {
			ps = conn.prepareStatement("INSERT INTO customer (name, surname, customertypeid, phone, email, companytitle, taxnumber, taxadministration, addressid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, c.getName());
			ps.setString(2, c.getSurname());
			ps.setInt(3, c.getCustomerType().getId());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getCompanyTitle());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());
			ps.setInt(9, c.getAddress().getId());
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
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("UPDATE customer SET name=?,surname=?,customertypeid=?,phone=?,email=?,companytitle=?,taxnumber=?,taxadministration=?,addressid=? where id=?");
			ps.setString(1, c.getName());
			ps.setString(2, c.getSurname());
			ps.setInt(3, c.getCustomerType().getId());
			ps.setString(4, c.getPhone());
			ps.setString(5, c.getEmail());
			ps.setString(6, c.getCompanyTitle());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());
			ps.setInt(9, c.getAddress().getId());
			ps.setInt(10, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM customer where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public CustomerTypeDAO getCustomerTypeDAO() {
		if (customerTypeDAO == null)
			customerTypeDAO = new CustomerTypeDAO();

		return customerTypeDAO;
	}

	public AddressDAO getAddressDAO() {
		if (addressDAO == null)
			addressDAO = new AddressDAO();

		return addressDAO;
	}

	public int count() {
		int count = 0;
		PreparedStatement ps;
		ResultSet rs;

		try {	
			ps = conn.prepareStatement("SELECT count(id) as customer_count from customer");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt("customer_count");

		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return count;
	}
}
