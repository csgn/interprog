package DAO;

import Model.Squad;
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
 * @author Metin
 */
public class SquadDAO implements IDAO<Squad> {

	@Override
	public Squad find(int id) {
		Squad squad = new Squad();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM squad WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				squad.setId(rs.getInt("id"));
				squad.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return squad;
	}

	@Override
	public List<Squad> findAll() {
		List<Squad> squads = new ArrayList<>();
		Squad squad;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM squad");
			rs = ps.executeQuery();

			while (rs.next()) {
				squad = new Squad(
								rs.getInt("id"),
								rs.getString("name")
				);

				squads.add(squad);
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return squads;
	}

		@Override
	public List<Squad> findAll(int page, int pageSize) {
		List<Squad> squads = new ArrayList<>();
		Squad squad;
		PreparedStatement ps;
		ResultSet rs;

		int start = (page - 1) * pageSize;

		try {
			ps = conn.prepareStatement("SELECT * FROM squad limit ? offset ?");
			ps.setInt(1, pageSize);
			ps.setInt(2, start);
			rs = ps.executeQuery();

			while (rs.next()) {
				squad = new Squad(
								rs.getInt("id"),
								rs.getString("name")
				);

				squads.add(squad);
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return squads;
	}

	@Override
	public int create(Squad s) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO squad (name) VALUES (?) RETURNING id");
			ps.setString(1, s.getName());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Squad s) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE squad SET name=? where id=?");
			ps.setString(1, s.getName());
			ps.setInt(2, s.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM squad WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Squad findByName(String name) {
		Squad squad = new Squad();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM squad WHERE name =?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				squad.setId(rs.getInt("id"));
				squad.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return squad;
	}

	public int count() {
		int count = 0;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT count(id) as squad_count from squad");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt("squad_count");

		} catch (SQLException ex) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return count;
	}
}
