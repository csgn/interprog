package DAO;

import Model.Role;
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
 * @author Metin
 */
public class RoleDAO implements IDAO<Role> {

	@Override
	public Role find(int id) {
		Role role = new Role();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM role WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return role;
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<>();
		Role role;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM role");
			rs = ps.executeQuery();

			while (rs.next()) {
				role = new Role(
								rs.getInt("id"),
								rs.getString("name")
				);
				roles.add(role);
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return roles;
	}

	@Override
	public int create(Role r) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO role (name) VALUES (?) RETURNING id");
			ps.setString(1, r.getName());

			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Role r) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE role SET name=? where id=?");
			ps.setString(1, r.getName());
			ps.setInt(2, r.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("DELETE FROM role WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Role findByName(String name) {
		PreparedStatement ps;
		ResultSet rs;
		Role role = new Role();

		try {
			ps = conn.prepareStatement("SELECT * FROM role WHERE name =?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return role;
	}

	@Override
	public List<Role> findAll(int p, int ps) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
