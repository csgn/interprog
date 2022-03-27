/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.CorporateCustomer;
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

public class CorporateCustomerDAO implements IDAO<CorporateCustomer>{

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private CorporateCustomer tmp;
	private List<CorporateCustomer> corporateCustomers;

	public List<CorporateCustomer> findAll() {

		try {
			corporateCustomers = new ArrayList<>();
			this.ps = this.con.prepareStatement("Select * from corporatecustomer");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public CorporateCustomer findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public CorporateCustomer findByPhone(String phone) {

		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where phone =?");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public CorporateCustomer findByEmail(String email) {

		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where email =?");
			this.ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public CorporateCustomer findByAddressId(int addressId) {

		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where addressId =?");
			this.ps.setInt(1, addressId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<CorporateCustomer> findByAccountType(int accountTypeId) {

		corporateCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where accountTypeId =?");
			this.ps.setInt(1, accountTypeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public List<CorporateCustomer> findBycompanyTitle(String companyTitle) {

		corporateCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where companytitle =?");
			this.ps.setString(1, companyTitle);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public List<CorporateCustomer> findByAuthorizedPerson(String authorizedPerson) {

		corporateCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where authorizedperson =?");
			this.ps.setString(1, authorizedPerson);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public List<CorporateCustomer> findByTaxNumber(String taxNumber) {

		corporateCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where taxNumber =?");
			this.ps.setString(1, taxNumber);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public List<CorporateCustomer> findByTaxAdministration(String taxAdministration) {

		corporateCustomers = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from corporatecustomer where taxadministration =?");
			this.ps.setString(1, taxAdministration);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new CorporateCustomer(
								rs.getInt("id"),
								rs.getString("phone"),
								rs.getString("email"),
								rs.getInt("addressId"),
								rs.getInt("accountTypeId"),
								rs.getString("companyTitle"),
								rs.getString("authorizedPerson"),
								rs.getString("taxNumber"),
								rs.getString("taxAdministration"));
				corporateCustomers.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return corporateCustomers;
	}

	public void insert(int id, String phone, String email, int addressId, int accountTypeId, String companyTitle, String authorizedPerson, String taxNumber, String taxAdministration) {
		try {
			this.ps = this.con.prepareStatement("insert into accounttype values (id = ? ,phone = ?, email = ?, accounttypeid = ?, companytitle = ?, authorizedperson = ?, taxnumber = ?, taxadministration = ?)");
			this.ps.setInt(1, id);
			this.ps.setString(2, phone);
			this.ps.setString(3, email);
			this.ps.setInt(4, addressId);
			this.ps.setInt(5, accountTypeId);
			this.ps.setString(6, companyTitle);
			this.ps.setString(7, authorizedPerson);
			this.ps.setString(8, taxNumber);
			this.ps.setString(9, taxAdministration);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteById(int id) {
		try {
			this.ps = this.con.prepareStatement("delete from corporatecustomer where id = ?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByEmail(String email){
		try {
			this.ps = this.con.prepareStatement("delete from corporatecustomer where email = ?");
			this.ps.setString(1, email);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public CorporateCustomer find(int id) {
		CorporateCustomer corporateCustomer = new CorporateCustomer();

		try {
			ps = con.prepareStatement("SELECT * FROM corporateCustomerr WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				corporateCustomer.setId(rs.getInt("id"));
				corporateCustomer.setPhone(rs.getString("phone"));
				corporateCustomer.setEmail(rs.getString("email"));
				corporateCustomer.setAddressId(rs.getInt("addressId"));
				corporateCustomer.setAccountTypeId(rs.getInt("accountTypeId"));
				corporateCustomer.setCompanyTitle(rs.getString("companyTitle"));
				corporateCustomer.setAuthorizedPerson(rs.getString("authorizedPerson"));
				corporateCustomer.setTaxNumber(rs.getString("taxNumber"));
				corporateCustomer.setTaxAdministration(rs.getString("taxAdministration"));
			}
		} catch (SQLException e) {
			Logger.getLogger(CorporateCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return corporateCustomer;
	}

	@Override
	public int create(CorporateCustomer c) {
		int id = -1;
		
		try {
			ps = con.prepareStatement("INSERT INTO corporateCustomer (phone, email, addressid, accounttypeid, companytitle, authorizedperson, taxnumber, taxadministration) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, c.getPhone());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getAddressId());
			ps.setInt(4, c.getAccountTypeId());
			ps.setString(5, c.getCompanyTitle());
			ps.setString(6, c.getAuthorizedPerson());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(CorporateCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}

	@Override
	public void update(CorporateCustomer c) {
		try {
			ps = con.prepareStatement("UPDATE corporate set phone=?,email=?,addressid=?,accounttypeid=?,companytitle=?,authorizedperson=?, taxnumber=?, taxAdministration=? where id=?");
			ps.setString(1, c.getPhone());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getAddressId());
			ps.setInt(4, c.getAccountTypeId());
			ps.setString(5, c.getCompanyTitle());
			ps.setString(6, c.getAuthorizedPerson());
			ps.setString(7, c.getTaxNumber());
			ps.setString(8, c.getTaxAdministration());
			ps.setInt(9, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CorporateCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	

	@Override
	public void delete(int id) {
		try {
			ps = con.prepareStatement("DELETE FROM corporatecustomer where id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(CorporateCustomer.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}