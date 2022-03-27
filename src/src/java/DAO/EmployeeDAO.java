package DAO;

import Model.Employee;
import java.util.List;
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

	private Employee employee;
	List<Employee> employees;
	private PreparedStatement ps;
	private ResultSet rs;
	private RoleDAO roleDAO;

	@Override
	public Employee find(int id) {
		employee = new Employee();

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
				employee.setRole(this.getRoleDAO().find(rs.getInt("roleid")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employee;
	}

	@Override
	public List<Employee> findAll() {
		employees = new ArrayList<>();
		employee = new Employee();

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
								this.getRoleDAO().find(rs.getInt("roleid"))
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
		try {
			ps = conn.prepareStatement("INSERT INTO employee (name, surname, phone, color, password, roleid) VALUES (?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, e.getName());
			ps.setString(2, e.getSurname());
			ps.setString(3, e.getPhone());
			ps.setString(4, e.getColor());
			ps.setString(5, e.getPassword());
			ps.setInt(6, e.getRole().getId());

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
		try {
			ps = conn.prepareStatement("UPDATE employee SET name=?,surname=?,phone=?,color=?,password=?,roleid=? where id=?");
			ps.setString(1, e.getName());
			ps.setString(2, e.getSurname());
			ps.setString(3, e.getPhone());
			ps.setString(4, e.getColor());
			ps.setString(5, e.getPassword());
			ps.setInt(6, e.getRole().getId());
			ps.setInt(7, e.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		try {
			ps = conn.prepareStatement("DELETE FROM employee where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Employee findByPhone(String phoneNumber) {
		employee = null;

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
								this.getRoleDAO().find(rs.getInt("roleId")));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employee;
	}

	public boolean validate(String user, String password) {
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

	public RoleDAO getRoleDAO() {
		if (roleDAO == null) {
			roleDAO = new RoleDAO();
		}

		return roleDAO;
	}

	public List<Employee> getJobEmployees(int jobId) {
		List<Employee> jobEmployees = new ArrayList<>();

		try {
			ps = conn.prepareStatement("select * from jobxemployee where jobId = ?");
			ps.setInt(1, jobId);
			rs = ps.executeQuery();

			while (rs.next()) {
				jobEmployees.add(this.find(rs.getInt("employeeId")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}



		
		return jobEmployees;
	}
}
