/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Activity;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.Date;
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
public class ActivityDAO implements IDAO<Activity> {
	
	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Activity tmp;
	private List<Activity> activities;
	
	public Activity find(int id){
		Activity activity = new Activity();
		try{
			this.ps = con.prepareStatement("SELECT * FROM activity WHERE id=?");
			this.ps.setInt(1,id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				activity.setId(rs.getInt("id"));
				activity.setDate(rs.getDate("date"));
				activity.setInfo(rs.getString("info"));
				activity.setJobId(rs.getInt("id"));
				activity.setMsg(rs.getString("msg"));
				activity.setOwnerId(rs.getInt("ownerId"));
				activity.setType((Activity)rs.getObject("type"));
			}
		}catch(SQLException e){
			Logger.getLogger(AccountTypeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return activity ;
	}
	
	public List<Activity> findAll() {

		try {
			activities = new ArrayList<>();
			this.ps = this.con.prepareStatement("Select * from activity");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Activity(
								rs.getInt("id"),
								rs.getDate("date"),
								rs.getString("msg"),
								rs.getString("info"),
								(Activity) rs.getObject("type"),
								rs.getInt("ownerId"),
								rs.getInt("jobId"));
				activities.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return activities;
	}
	
	public Activity findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * from activity where id = ?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Activity(
								rs.getInt("id"),
								rs.getDate("date"),
								rs.getString("msg"),
								rs.getString("info"),
								(Activity) rs.getObject("type"),
								rs.getInt("ownerId"),
								rs.getInt("jobId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public Activity findByOwnerId(int ownerId) {

		try {
			this.ps = this.con.prepareStatement("Select * from activity where ownerid = ?");
			this.ps.setInt(1, ownerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Activity(
								rs.getInt("id"),
								rs.getDate("date"),
								rs.getString("msg"),
								rs.getString("info"),
								(Activity) rs.getObject("type"),
								rs.getInt("ownerId"),
								rs.getInt("jobId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public Activity findByJobId(int jobId) {

		try {
			this.ps = this.con.prepareStatement("Select * from activity where jobid = ?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Activity(
								rs.getInt("id"),
								rs.getDate("date"),
								rs.getString("msg"),
								rs.getString("info"),
								(Activity) rs.getObject("type"),
								rs.getInt("ownerId"),
								rs.getInt("jobId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public int create(Activity a) {
		int id = -1;

		try {
			ps = con.prepareStatement("INSERT INTO activity (id, date, msg, info, type, ownerId, jobId) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setInt(1, a.getId());
			ps.setDate(2, (Date) a.getDate());
			ps.setString(3, a.getMsg());
			ps.setString(4, a.getInfo());
			ps.setObject(5, a.getType());
			ps.setInt(6, a.getOwnerId());
			ps.setInt(7, a.getJobId());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}
	
	public void update(Activity a) {

		try {
			ps = con.prepareStatement("UPDATE activity set date=?,msg=?,info=?,type=?,ownerid=?, jobid where id=?");
			ps.setDate(1, (Date) a.getDate());
			ps.setString(2, a.getMsg());
			ps.setString(3, a.getInfo());
			ps.setObject(4, a.getType());
			ps.setInt(5, a.getOwnerId());
			ps.setInt(6, a.getJobId());
			ps.setInt(7, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void delete(int id) {

		try {
			ps = con.prepareStatement("DELETE FROM activity where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
