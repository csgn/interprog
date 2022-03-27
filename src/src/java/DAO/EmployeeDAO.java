package DAO;

import Model.Employee;
import Utils.PGConn;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author csgn
 */
public class EmployeeDAO implements IDAO<Employee> {

	Connection conn = PGConn.getConnection();

	@Override
	public Employee find(int id) {
		Employee employee = new Employee();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM employee WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setSurname(rs.getString("surname"));
				employee.setPhone(rs.getString("phone"));
				employee.setColor(rs.getString("color"));
				employee.setPassword(rs.getString("password"));
				employee.setRoleId(rs.getInt("roleid"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employee;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<>();
		Employee employee;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM employee");
			rs = ps.executeQuery();

			while (rs.next()) {
				employee = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleid")
				);

				employees.add(employee);

			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employees;
	}

	@Override
	public int create(Employee e) {
		int id = -1;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("INSERT INTO employee (name, surname, phone, color, password, roleid) VALUES (?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, e.getName());
			ps.setString(2, e.getSurname());
			ps.setString(3, e.getPhone());
			ps.setString(4, e.getColor());
			ps.setString(5, e.getPassword());
			ps.setInt(6, e.getRoleId());

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
	public void update(Employee e) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("UPDATE employee SET name=?,surname=?,phone=?,color=?,password=?,roleid=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getSurname());
			ps.setString(3, e.getPhone());
			ps.setString(4, e.getColor());
			ps.setString(5, e.getPassword());
			ps.setInt(6, e.getRoleId());
			ps.setInt(7, e.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM employee where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Employee findByPhone(String phoneNumber) {
		Employee employee = null;
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT * FROM employee WHERE phone = ?");
			ps.setString(1, phoneNumber);
			rs = ps.executeQuery();
			while (rs.next()) {
				employee = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employee;
	}

	public boolean validate(String user, String password) {
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("SELECT phone, password, roleid FROM employee WHERE phone = ? AND password = ?");
			ps.setString(1, user);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error -->" + e.getMessage());
			return false;
		}

		return false;
	}

}
