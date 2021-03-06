package DAO;

import Model.Employee;
import Model.Squad;
import Model.Warehouse;
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
	private RoleDAO roleDAO;
	private SquadDAO squadDAO;
	private WarehouseDAO warehouseDAO;

	@Override
	public Employee find(int id) {
		employee = new Employee();
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
								this.getRoleDAO().find(rs.getInt("roleid")),
								getEmployeeSquads(rs.getInt("id")),
								getEmployeeWarehouses(rs.getInt("id"))
				);

				employees.add(employee);

			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employees;
	}

	@Override
	public List<Employee> findAll(int page, int pageSize) {
		employees = new ArrayList<>();
		employee = new Employee();
		PreparedStatement ps;
		ResultSet rs;

		int start = (page-1)*pageSize;

		try {
			ps = conn.prepareStatement("SELECT * FROM employee limit ? offset ?");
			ps.setInt(1, pageSize);
			ps.setInt(2, start);
			rs = ps.executeQuery();

			while (rs.next()) {
				employee = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								this.getRoleDAO().find(rs.getInt("roleid")),
								getEmployeeSquads(rs.getInt("id")),
								getEmployeeWarehouses(rs.getInt("id"))
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
		PreparedStatement ps;

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

		updateEmployeeSquads(e.getId(), e.getEmployeeSquads());
		updateEmployeeWarehouses(e.getId(), e.getEmployeeWarehouses());
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
		employee = null;
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
								getRoleDAO().find(rs.getInt("roleId")),
								getEmployeeSquads(rs.getInt("id")),
								getEmployeeWarehouses(rs.getInt("id"))
				);
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

	public void updateEmployeeSquads(int employeeId, List<Squad> squads) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("delete from employeexsquad where employeeId=?");
			ps.setInt(1, employeeId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			for (var squad : squads) {
				ps = conn.prepareStatement("insert into employeexsquad (employeeid, squadid) values (?, ?)");
				ps.setInt(1, employeeId);
				ps.setInt(2, squad.getId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<Squad> getEmployeeSquads(int employeeId) {
		List<Squad> employeeSquads = new ArrayList<>();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("select * from employeexsquad where employeeid = ?");
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();

			while (rs.next()) {
				employeeSquads.add(getSquadDAO().find(rs.getInt("squadId")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employeeSquads;
	}

	public void createEmployeeSquad(int employeeId, int squadId) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("insert into employeexsquad (employeeid, squadId) values (?, ?)");
			ps.setInt(1, employeeId);
			ps.setInt(2, squadId);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(SquadDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public RoleDAO getRoleDAO() {
		if (roleDAO == null) {
			roleDAO = new RoleDAO();
		}

		return roleDAO;
	}

	public SquadDAO getSquadDAO() {
		if (squadDAO == null) {
			squadDAO = new SquadDAO();
		}

		return squadDAO;
	}

	public WarehouseDAO getWarehouseDAO() {
		if (warehouseDAO == null) {
			warehouseDAO = new WarehouseDAO();
		}

		return warehouseDAO;
	}

	public void updateEmployeeWarehouses(int employeeId, List<Warehouse> warehouses) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("delete from employeexwarehouse where employeeId=?");
			ps.setInt(1, employeeId);
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			for (var warehouse : warehouses) {
				ps = conn.prepareStatement("insert into employeexwarehouse (employeeid, warehouseid) values (?, ?)");
				ps.setInt(1, employeeId);
				ps.setInt(2, warehouse.getId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public List<Warehouse> getEmployeeWarehouses(int employeeId) {
		List<Warehouse> employeeWarehouses = new ArrayList<>();
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement("select * from employeexwarehouse where employeeid = ?");
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();

			while (rs.next()) {
				employeeWarehouses.add(getWarehouseDAO().find(rs.getInt("warehouseId")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return employeeWarehouses;
	}

	public void createEmployeeWarehouse(int employeeId, int warehouseId) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("insert into employeexwarehouse (employeeid, warehouseId) values (?, ?)");
			ps.setInt(1, employeeId);
			ps.setInt(2, warehouseId);
			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(WarehouseDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}


	public int count() {
		int count = 0;
		PreparedStatement ps;
		ResultSet rs;

		try {	
			ps = conn.prepareStatement("SELECT count(id) as employee_count from employee");
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt("employee_count");

		} catch (SQLException ex) {
			Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return count;
	}

}
