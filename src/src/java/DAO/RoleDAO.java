
package DAO;

import Model.Role;
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
 * @author csgn
 */
public class RoleDAO implements IDAO<Role> {
	Connection conn = PGConn.getConnection();

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
		} catch (SQLException ex) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return role;
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<Role>();
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
		} catch (SQLException ex) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return roles;
	}

	@Override
	public int create(Role e) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO role (name) VALUES (?) RETURNING id");
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getMetaData());
			}
		} catch (SQLException ex) {
			Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return id;
	}

	@Override
	public void update(Role e) {
	}

	@Override
	public void delete(int id) {
	}
}
