package DAO;

import Model.Status;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Metin
 */
public class StatusDAO implements IDAO<Status> {

	@Override
	public Status find(int id) {
		Status status = new Status();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM status WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("name"));
				status.setColor(rs.getString("color"));
			}
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return status;
	}

	@Override
	public List<Status> findAll() {
		List<Status> statuses = new ArrayList<>();
		Status status;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM status");
			rs = ps.executeQuery();

			while (rs.next()) {
				status = new Status(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("color")
				);
				statuses.add(status);
			}
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return statuses;
	}
	@Override
	public List<Status> findAll(int page, int pageSize) {
		List<Status> statuses = new ArrayList<>();
		Status status = new Status();
		PreparedStatement ps;
		ResultSet rs;

		int start = (page-1)*pageSize;

		System.out.println(start + " " + pageSize);

		try {
			ps = conn.prepareStatement("SELECT * FROM status limit ? offset ?");
			ps.setInt(1, pageSize);
			ps.setInt(2, start);
			rs = ps.executeQuery();

			while (rs.next()) {
				status = new Status(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("color")
				);
				statuses.add(status);
			}
		}catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return statuses;
	}

	@Override
	public int create(Status s) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO status (name, color) VALUES (?, ?) RETURNING id");
			ps.setString(1, s.getName());
			ps.setString(2, s.getColor());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Status s) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE status SET name=?, color=? where id=?");
			ps.setString(1, s.getName());
			ps.setString(2, s.getColor());
			ps.setInt(3, s.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM status WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Status findByName(String name) {
		Status status = new Status();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM status WHERE name =?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("name"));
				status.setColor(rs.getString("color"));
			}
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return status;
	}

	public Status findByColor(String color) {
		Status status = new Status();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM status WHERE color =?");
			ps.setString(1, color);
			rs = ps.executeQuery();
			while (rs.next()) {
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("name"));
				status.setColor(rs.getString("color"));
			}
		} catch (SQLException e) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return status;
	}

	public int count() {
		int count = 0;
		PreparedStatement ps;
		ResultSet rs;

		try {	
			ps = conn.prepareStatement("SELECT count(id) as status_count from status");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt("status_count");

		} catch (SQLException ex) {
			Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return count;
	}
}
