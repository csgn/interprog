package DAO.core;

import Model.Job;
import Utils.PGConn;
import java.sql.Array;
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
 * @author csgn
 */
public class JobDAO implements IDAO<Job> {

	Connection conn = PGConn.getConnection();

	@Override
	public Job find(int id) {
		Job job = new Job();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM job WHERE id=?");
			ps.setInt(0, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				job.setId(rs.getInt("id"));
				job.setCreationDate(rs.getDate("creationDate"));
				job.setDescription(rs.getString("description"));
				job.setDate(rs.getDate("date"));
				job.setFiles((List<String>) rs.getArray("files"));
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
								(List<String>) rs.getArray("files"),
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
			ps = conn.prepareStatement("INSERT INTO job (creationDate,description,date,files,statusId,ownerId,customerId) VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setDate(0, (Date) j.getCreationDate());
			ps.setString(1, j.getDescription());
			ps.setDate(2, (Date) j.getDate());
			ps.setArray(3, (Array) j.getFiles());
			ps.setInt(4, j.getStatusId());
			ps.setInt(5, j.getOwnerId());
			ps.setInt(6, j.getCustomerId());

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
	public void delete(Job j) {
	}

}
