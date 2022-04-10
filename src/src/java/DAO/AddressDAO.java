package DAO;

import Model.Address;
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
public class AddressDAO implements IDAO<Address> {

	private Address address;
	private List<Address> addresses;

	@Override
	public Address find(int id) {
		PreparedStatement ps;
		ResultSet rs;
		address = new Address();

		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				address.setId(rs.getInt("id"));
				address.setContext(rs.getString("context"));
				address.setDirections(rs.getString("directions"));
				address.setDistrict(rs.getString("district"));
				address.setRegion(rs.getString("region"));
				address.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return address;
	}

	@Override
	public List<Address> findAll() {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM address");
			rs = ps.executeQuery();

			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions")
				);

				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return addresses;
	}

	@Override
	public int create(Address a) {
		PreparedStatement ps;
		ResultSet rs;
		int id = -1;

		try {
			ps = conn.prepareStatement("INSERT INTO address (title, context, region, district, directions) VALUES (?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getContext());
			ps.setString(3, a.getRegion());
			ps.setString(4, a.getDistrict());
			ps.setString(5, a.getDirections());

			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}

		return id;
	}

	@Override
	public void update(Address a) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("UPDATE address SET title=?, context=?, region=?, district=?, directions=? where id=?");
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getContext());
			ps.setString(3, a.getRegion());
			ps.setString(4, a.getDistrict());
			ps.setString(5, a.getDirections());
			ps.setInt(6, a.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement("DELETE FROM address where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<Address> findByDirections(String directions) {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();
		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE directions = ?");
			ps.setString(1, directions);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return addresses;
	}

	public List<Address> findByDistrict(String district) {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE district = ?");
			ps.setString(1, district);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return addresses;
	}

	public List<Address> findByRegion(String region) {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE region = ?");
			ps.setString(1, region);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return addresses;
	}

	public List<Address> findByContext(String context) {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE context = ?");
			ps.setString(1, context);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return addresses;
	}

	public List<Address> findByTitle(String title) {
		PreparedStatement ps;
		ResultSet rs;
		addresses = new ArrayList<>();

		try {
			ps = conn.prepareStatement("SELECT * FROM address WHERE title = ?");
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = new Address(
								rs.getInt("id"),
								rs.getString("title"),
								rs.getString("context"),
								rs.getString("region"),
								rs.getString("district"),
								rs.getString("directions"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return addresses;
	}
}
