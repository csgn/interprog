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

/**
 *
 * @author Aykut
 */
public class AddressDAO {

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

	public void insert(int id, String title, String context, String region, String district, String directions) {
		try {
			this.ps = this.con.prepareStatement("insert into accounttype values (id = ? ,title = ?, region = ?, district = ?, directions = ?)");
			this.ps.setInt(1, id);
			this.ps.setString(2, title);
			this.ps.setString(3, region);
			this.ps.setString(4, district);
			this.ps.setString(5, directions);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteById(int id) {
		try {
			this.ps = this.con.prepareStatement("delete from accounttype values (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
