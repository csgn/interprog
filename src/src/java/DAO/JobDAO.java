package DAO;

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
			ps = conn.prepareStatement("INSERT INTO job (creationdate, description, date, files, statusid, ownerid, customerid) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setDate(1, (Date) j.getCreationDate());
			ps.setString(2, j.getDescription());
			ps.setDate(3, (Date) j.getDate());
			ps.setArray(4, (Array) j.getFiles());
			ps.setInt(5, j.getStatusId());
			ps.setInt(6, j.getOwnerId());
			ps.setInt(7, j.getCustomerId());
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
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("UPDATE job SET creationdate=?,description=?,date=?,files=?,statusid=?,ownerid=?, customerid where id=?");
			ps.setDate(1, (Date) j.getCreationDate());
			ps.setString(2, j.getDescription());
			ps.setDate(3, (Date) j.getDate());
			ps.setArray(4, (Array) j.getFiles());
			ps.setInt(5, j.getStatusId());
			ps.setInt(6, j.getOwnerId());
			ps.setInt(7, j.getCustomerId());
			ps.setInt(8, j.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM job where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(JobDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
