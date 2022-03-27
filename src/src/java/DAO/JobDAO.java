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
 *
 * @author csgn
 */
public class JobDAO implements IDAO<Job> {

	@Override
	public Job find(int id) {
		Job job = new Job();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM job WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				job.setId(rs.getInt("id"));
				job.setCreationDate(rs.getDate("creationDate"));
				job.setDescription(rs.getString("description"));
				job.setDate(rs.getDate("date"));
				job.setStatusId(rs.getInt("statusId"));
				job.setOwnerId(rs.getInt("ownerId"));
				job.setCustomerId(rs.getInt("customerId"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return job;
	}

	@Override
	public List findAll() {
		List<Job> jobs = new ArrayList<>();
		Job job;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM job");
			rs = ps.executeQuery();

			while (rs.next()) {
				job = new Job(
								rs.getInt("id"),
								rs.getDate("creationDate"),
								rs.getString("description"),
								rs.getDate("date"),
								rs.getInt("statusId"),
								rs.getInt("ownerId"),
								rs.getInt("customerId")
				);

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
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO job (description,date,customerId) VALUES (?, ?, ?) RETURNING id");
			ps.setString(1, j.getDescription());
			ps.setDate(2, new Date(j.getDate().getTime()));
			ps.setInt(3, j.getCustomerId());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;
	}

	@Override
	public void update(Job j) {
	}

	@Override
	public void delete(int id) {
	}

}
