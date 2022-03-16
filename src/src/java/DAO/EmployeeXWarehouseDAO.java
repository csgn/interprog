/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.EmployeeXWarehouse;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aykut
 */
public class EmployeeXWarehouseDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private EmployeeXWarehouse tmp;
	private List<EmployeeXWarehouse> employeeXWarehouses;

	public List<EmployeeXWarehouse> findAll() {

		employeeXWarehouses = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employeexwarehouse");
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
			this.ps = this.con.prepareStatement("Select * from employeexwarehouse where employeeid =?");
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
			this.ps = this.con.prepareStatement("Select * from employeexsquad where warehouseid =?");
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
}
