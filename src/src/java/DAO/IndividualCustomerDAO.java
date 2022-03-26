/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Employee;
import Model.IndividualCustomer;
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
public class IndividualCustomerDAO implements IDAO<IndividualCustomer>{

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private IndividualCustomer tmp;
	private List<IndividualCustomer> individualCustomers;

	public List<IndividualCustomer> findAll() {

		individualCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
				individualCustomers.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return individualCustomers;
	}

	public IndividualCustomer findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public IndividualCustomer findByPhone(String phone) {

		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where phone = ?");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public IndividualCustomer findByEmail(String email) {

		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where email =?");
			this.ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public IndividualCustomer findByAdressId(int addressId) {

		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where addressid =?");
			this.ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public IndividualCustomer findByAccountTypeId(int accountTypeId) {

		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where accountTypeId =?");
			this.ps.setInt(1, accountTypeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<IndividualCustomer> findByCompanyTitle(String companyTitle) {

		individualCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where companytitle = ?");
			this.ps.setString(1, companyTitle);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
				individualCustomers.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return individualCustomers;
	}

	public List<IndividualCustomer> findByName(String name) {

		individualCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where name = ?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
				individualCustomers.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return individualCustomers;
	}

	public List<IndividualCustomer> findBySurname(String surname) {

		individualCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from individualcustomer where surname = ?");
			this.ps.setString(1, surname);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new IndividualCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("name"),
								rs.getString("surname"));
				individualCustomers.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return individualCustomers;
	}
	
	public void deleteByNameAndSurname(String name, String surname){
		try {
			this.ps = this.con.prepareStatement("delete from individualcustomer where (name = ? and surname = ?)");
			this.ps.setString(1, name);
			this.ps.setString(2, surname);
			ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	@Override
	public IndividualCustomer find(int id) {
		IndividualCustomer individualCustomer = new IndividualCustomer();

		try {
			ps = con.prepareStatement("SELECT * FROM individualCustomer WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				individualCustomer.setId(rs.getInt("id"));
				individualCustomer.setPhone(rs.getString("phone"));
				individualCustomer.setEmail(rs.getString("email"));
				individualCustomer.setAddressId(rs.getInt("addressId"));
				individualCustomer.setAccountTypeId(rs.getInt("accountTypeId"));
				individualCustomer.setCompanyTitle(rs.getString("companyTitle"));
				individualCustomer.setName(rs.getString("name"));
				individualCustomer.setSurname(rs.getString("surname"));
			}
		} catch (SQLException e) {
			Logger.getLogger(IndividualCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return individualCustomer;
	}

	@Override
	public int create(IndividualCustomer i) {
		int id = -1;
		
		try {
			ps = con.prepareStatement("INSERT INTO individualCustomer (phone, email, addressid, accounttypeid, companytitle, name, surname) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, i.getPhone());
			ps.setString(2, i.getEmail());
			ps.setInt(3, i.getAddressId());
			ps.setInt(4, i.getAccountTypeId());
			ps.setString(5, i.getCompanyTitle());
			ps.setString(6, i.getName());
			ps.setString(7, i.getSurname());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;
	}

	@Override
	public void delete(int id) {
		
		try {
			ps = con.prepareStatement("DELETE FROM IndividualCustomer where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(IndividualCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void update(IndividualCustomer i) {
		 
		try {
			ps = con.prepareStatement("UPDATE individualcustomer set phone=?,email=?,addressid=?,accounttypeid=?,companytitle=?,name=?, surname=? where id=?");
			ps.setString(1, i.getPhone());
			ps.setString(2, i.getEmail());
			ps.setInt(3, i.getAddressId());
			ps.setInt(4, i.getAccountTypeId());
			ps.setString(5, i.getCompanyTitle());
			ps.setString(6, i.getName());
			ps.setString(7, i.getSurname());
			ps.setInt(8, i.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
