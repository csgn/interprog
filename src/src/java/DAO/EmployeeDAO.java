package DAO;

import Model.Employee;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Metin
 */
public class EmployeeDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;

	public List<Employee> findAll() {
		List<Employee> employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee");
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				Employee tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
			return employees;
		} catch (SQLException e) {
			System.out.println("Error -->" + e.getMessage());
		}
		return employees;
	}

	public Employee findById(int id) {
		try {
			this.ps = this.con.prepareStatement("Select * from employee where id = ?");
			this.ps.setInt(1, id);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				Employee tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				return tmp;
			}
		} catch (SQLException e) {
			System.out.println("Error -->" + e.getMessage());
		}
		return null;
	}

	public Employee findByPhoneNumber(String phoneNumber) {
		try {
			this.ps = this.con.prepareStatement("Select * from employee where phone = ?");
			this.ps.setString(1, phoneNumber);
			ResultSet rs = this.ps.executeQuery();
			if (rs.next()) {
				Employee tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				return tmp;
			}
		} catch (SQLException e) {
			System.out.println("Error -->" + e.getMessage());
		}
		return null;
	}

	public boolean validate(String user, String password) {
		try {
			this.ps = this.con.prepareStatement("Select phone, password, roleid from employee where phone = ? and password = ?");
			this.ps.setString(1, user);
			this.ps.setString(2, password);

			ResultSet rs = this.ps.executeQuery();

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
