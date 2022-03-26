package DAO;

import Model.JobXEmployee;
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
public class JobXEmployeeDAO implements IDAO<JobXEmployee> {

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
	
	public void insert(int jobId, int employeeId){
		
		try{
			this.ps = this.con.prepareStatement("insert into jobxemployee values (jobid = ?, employeeid = ?)");
			this.ps.setInt(1,jobId);
			this.ps.setInt(2, employeeId);
			rs = ps.executeQuery();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByJobId(int jobId){
		
		try {
			this.ps = this.con.prepareStatement("delete from jobxemployee where (jobid = ?)");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByEmployeeId(int employeeId){
		
		try {
			this.ps = this.con.prepareStatement("delete from employeexwarehouse where (employeeid = ?)");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public JobXEmployee find(int jobId) {
		JobXEmployee jobXEmployee = new JobXEmployee();
		try {
			this.ps = con.prepareStatement("SELECT * FROM jobxemployee WHERE jobid=?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();

			while (rs.next()) {
				jobXEmployee.setJobId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			Logger.getLogger(JobXEmployee.class.getName()).log(Level.SEVERE, null, e);
		}
		return jobXEmployee;
	}

	@Override
	public int create(JobXEmployee j) {
		int jobId = -1;
		try {
			ps = con.prepareStatement("INSERT INTO jobxemployee (jobid, employeeid) VALUES (?, ?) RETURNING jobid");
			ps.setInt(1, j.getJobId());
			ps.setInt(2, j.getEmployeeId());
			rs = ps.executeQuery();
			while (rs.next()) {
				jobId = rs.getInt("jobId");
			}
		} catch (SQLException e) {
			Logger.getLogger(JobXEmployee.class.getName()).log(Level.SEVERE, null, e);
		}
		return jobId;
	}

	@Override
	public void update(JobXEmployee t) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void delete(int jobId) {
		try {
			ps = con.prepareStatement("DELETE FROM jobxemployee where jobId=?");
			ps.setInt(1, jobId);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(JobXEmployee.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
