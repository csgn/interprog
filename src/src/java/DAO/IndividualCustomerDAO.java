/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.IndividualCustomer;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aykut
 */
public class IndividualCustomerDAO {

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
	
	public void insert(int id, String phone, String email, int addressId, int accountTypeId, String companyTitle, String name, String surname){
		
		try{
			this.ps = this.con.prepareStatement("insert into individualcustomer values (id = ?, phone = ?, email = ?, addressId = ?, accountTypeId = ?, companyTitle = ?, name = ?, surname = ?)");
			this.ps.setInt(1, id);
			this.ps.setString(2, phone);
			this.ps.setString(3, email);
			this.ps.setInt(4, addressId);
			this.ps.setInt(5, accountTypeId);
			this.ps.setString(6, companyTitle);
			this.ps.setString(7, name);
			this.ps.setString(8, surname);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteById(int id){
		try {
			this.ps = this.con.prepareStatement("delete from individualcustomer where (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByNameAndSurname(String name, String surname){
		try {
			this.ps = this.con.prepareStatement("delete from individualcustomer where (name = ? and surname = ?)");
			this.ps.setString(1, name);
			this.ps.setString(2, surname);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByPhone(String phone){
		try {
			this.ps = this.con.prepareStatement("delete from individualcustomer where (phone = ?)");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
