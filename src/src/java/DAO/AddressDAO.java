/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Address;
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
public class AddressDAO implements IDAO<Address> {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Address tmp;
	private List<Address> addresses;

	public List<Address> findAll() {

		addresses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from address");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return addresses;
	}

	public Address findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from address where id = ?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<Address> findByTitle(String title) {

		addresses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from address where title = ?");
			this.ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return addresses;
	}

	public Address findByContext(String context) {

		try {
			this.ps = this.con.prepareStatement("Select * from address where context = ?");
			this.ps.setString(1, context);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public List<Address> findByRegion(String region) {

		addresses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from address where region = ?");
			this.ps.setString(1, region);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return addresses;
	}

	public List<Address> findByDistrict(String district) {

		addresses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from address where district = ?");
			this.ps.setString(1, district);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return addresses;
	}

	public List<Address> findByDirections(String directions) {

		addresses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from address where directions = ?");
			this.ps.setString(1, directions);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return addresses;
	}

	@Override
	public Address find(int id) {
		Address address = new Address();
		
		try {
			ps = con.prepareStatement("SELECT * FROM address WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				address.setId(rs.getInt("id"));
				address.setTitle(rs.getString("title"));
				address.setContext(rs.getString("context"));
				address.setRegion(rs.getString("region"));
				address.setDistrict(rs.getString("district"));
				address.setDirections(rs.getString("directions"));	
			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger(AddressDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		}	
		return address;
	}

	@Override
	public int create(Address a) {
		
		int id = -1;
		try {
			ps = con.prepareStatement("INSERT INTO address (title, context, region, district, directions) VALUES (?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getContext());
			ps.setString(3, a.getRegion());
			ps.setString(4, a.getDistrict());
			ps.setString(5, a.getDirections());
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}

	@Override
	public void update(Address a) {
		
		try {
			ps = con.prepareStatement("UPDATE adress set title=?,context=?,region=?,district=?,directions=? where id=?");
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getContext());
			ps.setString(3, a.getRegion());
			ps.setString(4, a.getDistrict());
			ps.setString(5, a.getDirections());
			ps.setInt(6, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		
		try {
			ps = con.prepareStatement("DELETE FROM address where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
