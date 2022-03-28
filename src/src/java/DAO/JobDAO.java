package DAO;

import Model.Job;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aykut
 * @author csgn
 */
public class JobDAO implements IDAO<Job> {

	private Job job;
	private List<Job> jobs;
	private PreparedStatement ps;
	private ResultSet rs;
	private StatusDAO statusDAO;
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	private ProductDAO productDAO;

	@Override
	public Job find(int id) {
		job = new Job();

		try {
			ps = conn.prepareStatement("SELECT * FROM job WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				job.setId(rs.getInt("id"));
				job.setCreationDate(rs.getDate("creationDate"));
				job.setDescription(rs.getString("description"));
				job.setDate(rs.getDate("date"));
				job.setStatus(getStatusDAO().find(rs.getInt("statusId")));
				job.setOwner(getEmployeeDAO().find(rs.getInt("ownerId")));
				job.setCustomer(getCustomerDAO().find(rs.getInt("customerId")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return job;
	}

	@Override
	public List findAll() {
		jobs = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM job");
			rs = ps.executeQuery();

			while (rs.next()) {
				job = new Job(
								rs.getInt("id"),
								rs.getDate("creationDate"),
								rs.getString("description"),
								rs.getDate("date"),
								getStatusDAO().find(rs.getInt("statusId")),
								getEmployeeDAO().find(rs.getInt("ownerId")),
								getCustomerDAO().find(rs.getInt("customerId")),
								getEmployeeDAO().getJobEmployees(rs.getInt("id")),
								getProductDAO().getJobProducts(rs.getInt("id")));
				jobs.add(job);
			}
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return jobs;
	}

	@Override
	public int create(Job j) {
		int id = -1;

		try {
			ps = conn.prepareStatement("INSERT INTO job (description, date, customerid) VALUES (?, ?, ?) RETURNING id");
			ps.setString(1, j.getDescription());
			ps.setDate(2, new java.sql.Date(j.getDate().getTime()));
			ps.setInt(3, j.getCustomer().getId());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	@Override
	public void update(Job j) {
		try {
			ps = conn.prepareStatement("UPDATE job SET creationdate=?,description=?,date=?,statusid=?,ownerid=?, customerid=? where id=?");
			ps.setDate(1, new java.sql.Date(j.getCreationDate().getTime()));
			ps.setString(2, j.getDescription());
			ps.setDate(3, new java.sql.Date(j.getDate().getTime()));
			ps.setInt(4, j.getStatus().getId());
			ps.setInt(5, j.getOwner().getId());
			ps.setInt(6, j.getCustomer().getId());
			ps.setInt(7, j.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		try {
			ps = conn.prepareStatement("DELETE FROM job where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public StatusDAO getStatusDAO() {
		if (statusDAO == null) {
			statusDAO = new StatusDAO();
		}

		return statusDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		if (employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
		}

		return employeeDAO;
	}

	public CustomerDAO getCustomerDAO() {
		if (customerDAO == null) {
			customerDAO = new CustomerDAO();
		}

		return customerDAO;
	}

	public ProductDAO getProductDAO() {
		if (productDAO == null) {
			productDAO = new ProductDAO();
		}

		return productDAO;
	}

}
