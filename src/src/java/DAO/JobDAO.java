/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Job;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aykut
public class JobDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Job tmp;
	private List<Job> jobs;

	public List<Job> findAll() {

		jobs = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from job");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								(List<String>) rs.getArray("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
				jobs.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return jobs;
	}

	public Job findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from job where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								(List<String>) rs.getArray("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Job findByCreationDate(Date date) {

		try {
			this.ps = this.con.prepareStatement("Select * from job where date =?");
			this.ps.setDate(1, (java.sql.Date) date);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								(List<String>) rs.getArray("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Job findByStatusId(int statusId) {

		try {
			this.ps = this.con.prepareStatement("Select * from job where statusid =?");
			this.ps.setInt(1, statusId);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								(List<String>) rs.getArray("files"),
								rs.getString("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Job findByOwnerId(int ownerId) {

		try {
			this.ps = this.con.prepareStatement("Select * from job where ownerid =?");
			this.ps.setInt(1, ownerId);
			rs = ps.executeQuery();

			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								(List<String>) rs.getArray("files"),
								rs.getString("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Job findByCustomerId(int customerId) {

		try {
			this.ps = this.con.prepareStatement("Select * from job where customerId =?");
			this.ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Job(
								rs.getInt("id"),
								rs.getDate("creationdate"),
								rs.getString("description"),
								rs.getDate("date"),
								rs.getString("files"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public void insert(int id, Date creationDate, String description, Date date, String files, int statusId, int ownerId, int customerId){
		try{
			this.ps = this.con.prepareStatement("insert into job values (id = ?, creationdate = ?, description = ?, date = ?, files = ?, statusid = ?, ownerid = ?, customerid = ?)");
			this.ps.setInt(1, id);
			this.ps.setDate(2, (java.sql.Date) creationDate);
			this.ps.setString(3, description);
			this.ps.setDate(4, (java.sql.Date) date);
			this.ps.setString(5, files);
			this.ps.setInt(6, statusId);
			this.ps.setInt(7, ownerId);
			this.ps.setInt(8, customerId);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteById(int id){
		
		try {
			this.ps = this.con.prepareStatement("delete from job where (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByCreationDate(Date creationDate){
		
		try {
			this.ps = this.con.prepareStatement("delete from job where (creationdate = ?)");
			this.ps.setDate(1, (java.sql.Date) creationDate);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByOwnerId(int ownerId){
		
		try {
			this.ps = this.con.prepareStatement("delete from job where (ownerid = ?)");
			this.ps.setInt(1, ownerId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByCustomerId(int customerId){
		
		try {
			this.ps = this.con.prepareStatement("delete from job where (customerid = ?)");
			this.ps.setInt(1, customerId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

 */