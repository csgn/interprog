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
}
