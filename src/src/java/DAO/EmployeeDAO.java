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
	private ResultSet rs;
	private Employee tmp;
	private List<Employee> employees;

	public List<Employee> findAll() {

		employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee");
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}

	public Employee findById(int id) {
		
		try {
			this.ps = this.con.prepareStatement("Select * from employee where id = ?");
			this.ps.setInt(1, id);
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
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
		return tmp;
	}

	public Employee findByPhoneNumber(String phoneNumber) {
		
		try {
			this.ps = this.con.prepareStatement("Select * from employee where phone = ?");
			this.ps.setString(1, phoneNumber);
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
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
		return tmp;
	}

	public boolean validate(String user, String password) {
		
		try {
			System.out.println("PHONE: " + user + " PASSWORD: " + password);
			this.ps = this.con.prepareStatement("Select phone, password, roleid from employee where phone = ? and password = ?");
			this.ps.setString(1, user);
			this.ps.setString(2, password);
			rs = this.ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error -->" + e.getMessage());
			return false;
		}
		return false;
	}

	public List<Employee> getAllEmployeeBySquadId() {
		
		employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee left join squad on employee.id = squad.id");
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("boyut: " + employees.size());
		return employees;
	}

	public List<Employee> findAllEmployeeByWarehouseId() {
		
		employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee left join warehouse on employee.id = warehouse.id");
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}

	public List<Employee> findAllEmployeesByJobId() {
		
		employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee left join job on employee.id = job.ownerid");
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}

	public List<Employee> findAllEmployeesByRoleId() {

		employees = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employee left join job on employee.roleid = role.id");
			rs = this.ps.executeQuery();
			while (rs.next()) {
				tmp = new Employee(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("surname"),
								rs.getString("phone"),
								rs.getString("color"),
								rs.getString("password"),
								rs.getInt("roleId"));
				employees.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("ok" + employees.size());
		return employees;
	}
	
	public void insert(int id, String name, String surname, String phone, String color, String password, int roleId){
		
		try {
			this.ps = this.con.prepareStatement("insert into employee values (id = ? ,name = ?, surname = ?, phone = ?, color = ?, password = ?, roleId = ? )");
			this.ps.setInt(1, id);
			this.ps.setString(2, name);
			this.ps.setString(3, surname);
			this.ps.setString(4, phone);
			this.ps.setString(5, color);
			this.ps.setString(6, password);
			this.ps.setInt(7, roleId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteById(int id){
		
		try {
			this.ps = this.con.prepareStatement("delete from employee where (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByNameAndSurname(String name, String surname){
		
		try {
			this.ps = this.con.prepareStatement("delete from employee where (name = ? and surname = ?)");
			this.ps.setString(1, name);
			this.ps.setString(2, surname);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByPhone(String phone){
		
		try {
			this.ps = this.con.prepareStatement("delete from employee where (phone = ?)");
			this.ps.setString(1, phone);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
