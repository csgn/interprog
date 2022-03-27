package DAO;

import Model.EmployeeXWarehouse;
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
 */
public class EmployeeXWarehouseDAO implements IDAO<EmployeeXWarehouse>{

	private PreparedStatement ps;
	private ResultSet rs;
	private EmployeeXWarehouse tmp;
	private List<EmployeeXWarehouse> employeeXWarehouses;

	public List<EmployeeXWarehouse> findAll() {

		employeeXWarehouses = new ArrayList<>();
		try {
			this.ps = this.conn.prepareStatement("Select * from employeexwarehouse");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXWarehouse(
								rs.getInt("employeeId"),
								rs.getInt("warehouseId"));
				employeeXWarehouses.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employeeXWarehouses;
	}

	public EmployeeXWarehouse findByEmployeeId(int employeeId) {

		try {
			this.ps = this.conn.prepareStatement("Select * from employeexwarehouse where employeeid =?");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXWarehouse(
								rs.getInt("employeeId"),
								rs.getInt("warehouseId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public EmployeeXWarehouse findByWarehouseId(int warehouseId) {

		try {
			this.ps = this.conn.prepareStatement("Select * from employeexswarehouse where warehouseid =?");
			this.ps.setInt(1, warehouseId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXWarehouse(
								rs.getInt("employeeId"),
								rs.getInt("warehouseId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public void insert(int employeeId, int warehouseId){
		
		try{
			this.ps = this.conn.prepareStatement("insert into employeexwarehouse values (employeeid = ?, warehouseid = ?)");
			this.ps.setInt(1,employeeId);
			this.ps.setInt(2, warehouseId);
			rs = ps.executeQuery();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteByEmployeeId(int employeeId){
		
		try {
			this.ps = this.conn.prepareStatement("delete from employeexwarehouse where (employeeid = ?)");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteBySquadId(int warehouseId){
		
		try {
			this.ps = this.conn.prepareStatement("delete from employeexwarehouse where (warehouseid = ?)");
			this.ps.setInt(1, warehouseId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public EmployeeXWarehouse find(int employeeId) {
		EmployeeXWarehouse employeeXWarehouse = new EmployeeXWarehouse();
		try {
			ps = conn.prepareStatement("SELECT * FROM employeexwarehouse WHERE employeeid=?");
			ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				employeeXWarehouse.setEmployeeId(rs.getInt("employeeId"));
				employeeXWarehouse.setWarehouseId(rs.getInt("warehouseId"));
			}
		} catch (SQLException e) {
			Logger.getLogger(EmployeeXWarehouse.class.getName()).log(Level.SEVERE, null, e);
		}
		return employeeXWarehouse;
	}

	@Override
	public int create(EmployeeXWarehouse e) {
		int employeeId = -1;
		try {
			ps = conn.prepareStatement("INSERT INTO employeexWarehouse (employeeid, warehouseid) VALUES (?, ?) RETURNING employeeid");
			ps.setInt(1, e.getEmployeeId());
			ps.setInt(2, e.getWarehouseId());
			rs = ps.executeQuery();
			while (rs.next()) {
				employeeId = rs.getInt("employeeId");
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeXWarehouse.class.getName()).log(Level.SEVERE, null, ex);
		}
		return employeeId;
	}

	@Override
	public void update(EmployeeXWarehouse t) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void delete(int employeeId) {
	try {
			ps = conn.prepareStatement("DELETE FROM employeexwarehouse where employeeid=?");
			ps.setInt(1, employeeId);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(EmployeeXWarehouse.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
}
