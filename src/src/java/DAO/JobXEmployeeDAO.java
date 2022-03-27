package DAO;

import Model.JobXEmployee;
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

	private PreparedStatement ps;
	private ResultSet rs;
	private JobXEmployee tmp;
	private List<JobXEmployee> jobXEmployees;

	private JobDAO jobDAO;
	private EmployeeDAO employeeDAO;
	private ProductDAO productDAO;

	public List<JobXEmployee> findAll() {

		jobXEmployees = new ArrayList<>();
		try {
			this.ps = this.conn.prepareStatement("Select * from jobxemployee");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								getJobDAO().find(rs.getInt("jobId")),
								getEmployeeDAO().find(rs.getInt("employeeId"))
				);
				jobXEmployees.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return jobXEmployees;
	}

	public JobXEmployee findByJobId(int jobId) {

		try {
			this.ps = this.conn.prepareStatement("Select * from jobxemployeeid where jobid =?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								getJobDAO().find(rs.getInt("jobId")),
								getEmployeeDAO().find(rs.getInt("employeeId")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public JobXEmployee findByEmployeeId(int employeeId) {

		try {
			this.ps = this.conn.prepareStatement("Select * from employeexsquad where employeeid =?");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXEmployee(
								getJobDAO().find(rs.getInt("jobId")),
								getEmployeeDAO().find(rs.getInt("employeeId")));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	
	@Override
	public JobXEmployee find(int jobId) {
		JobXEmployee jobXEmployee = new JobXEmployee();
		try {
			this.ps = conn.prepareStatement("SELECT * FROM jobxemployee WHERE jobid=?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();

			while (rs.next()) {
				jobXEmployee.setJob(getJobDAO().find(rs.getInt("id")));
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
			ps = conn.prepareStatement("INSERT INTO jobxemployee (jobid, employeeid) VALUES (?, ?) RETURNING jobid");
			ps.setInt(1, j.getJob().getId());
			ps.setInt(2, j.getEmployee().getId());
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
			ps = conn.prepareStatement("DELETE FROM jobxemployee where jobId=?");
			ps.setInt(1, jobId);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(JobXEmployee.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public EmployeeDAO getEmployeeDAO() {
		if (employeeDAO == null)
			employeeDAO = new EmployeeDAO();

		return employeeDAO;
	}

	public ProductDAO getProductDAO() {
		if (productDAO == null)
			productDAO = new ProductDAO();

		return productDAO;
	}

	public JobDAO getJobDAO() {
		if (jobDAO == null) 
			jobDAO = new JobDAO();

		return jobDAO;
	}



	
}
