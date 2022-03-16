/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.JobXEmployee;
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
public class JobXEmployeeDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private JobXEmployee tmp;
	private List<JobXEmployee> jobXEmployees;

	public List<JobXEmployee> findAll() {

		jobXEmployees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from jobxemployee");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								rs.getInt("employeeId"),
								rs.getInt("jobId"));
				jobXEmployees.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return jobXEmployees;
	}

	public JobXEmployee findByJobId(int jobId) {

		try {
			this.ps = this.con.prepareStatement("Select * from jobxemployeeid where jobid =?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								rs.getInt("jobId"),
								rs.getInt("employeeId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public JobXEmployee findByEmployeeId(int employeeId) {

		try {
			this.ps = this.con.prepareStatement("Select * from employeexsquad where employeeid =?");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								rs.getInt("jobId"),
								rs.getInt("employeeId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
}
