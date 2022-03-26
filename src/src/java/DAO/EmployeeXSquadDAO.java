package DAO;

import Model.EmployeeXSquad;
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
 * @author Aykut
 */
public class EmployeeXSquadDAO implements IDAO<EmployeeXSquad> {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private EmployeeXSquad tmp;
	private List<EmployeeXSquad> employeeXSquads;

	public List<EmployeeXSquad> findAll() {

		employeeXSquads = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from employeexsquad");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXSquad(
								rs.getInt("employeeId"),
								rs.getInt("squadId"));
				employeeXSquads.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employeeXSquads;
	}

	public EmployeeXSquad findByEmployeeId(int employeeId) {

		try {
			this.ps = this.con.prepareStatement("Select * from employeexsquad where employeeid =?");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXSquad(
								rs.getInt("employeeId"),
								rs.getInt("squadId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public EmployeeXSquad findBySquadId(int squadId) {

		try {
			this.ps = this.con.prepareStatement("Select * from employeexsquad where squadid =?");
			this.ps.setInt(1, squadId);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new EmployeeXSquad(
								rs.getInt("employeeId"),
								rs.getInt("squadId"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public void insert(int employeeId, int squadId) {
		try {
			this.ps = this.con.prepareStatement("insert into employeexsquad values (employeeid = ?, squadid = ?)");
			this.ps.setInt(1, employeeId);
			this.ps.setInt(2, squadId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteByEmployeeId(int employeeId) {

		try {
			this.ps = this.con.prepareStatement("delete from employeexsquad where (employeeid = ?)");
			this.ps.setInt(1, employeeId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteBySquadId(int squadId) {

		try {
			this.ps = this.con.prepareStatement("delete from employeexsquad where (squadid = ?)");
			this.ps.setInt(1, squadId);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public EmployeeXSquad find(int employeeId) {
		EmployeeXSquad employeeXSquad = new EmployeeXSquad();
		try {
			ps = con.prepareStatement("SELECT * FROM employeexsquad WHERE employeeid=?");
			ps.setInt(1, employeeId);

			rs = ps.executeQuery();

			while (rs.next()) {
				employeeXSquad.setEmployeeId(rs.getInt("employeeId"));
				employeeXSquad.setSquadId(rs.getInt("squadId"));

			}
		} catch (SQLException e) {
			Logger.getLogger(EmployeeXSquad.class.getName()).log(Level.SEVERE, null, e);
		}
		return employeeXSquad;
	}

	@Override
	public int create(EmployeeXSquad e) {

		int employeeId = -1;
		try {
			ps = con.prepareStatement("INSERT INTO employeexsquad (employeeid, squadid) VALUES (?, ?) RETURNING employeeid");
			ps.setInt(1, e.getEmployeeId());
			ps.setInt(2, e.getSquadId());
			rs = ps.executeQuery();
			while (rs.next()) {
				employeeId = rs.getInt("employeeId");
			}
		} catch (SQLException ex) {
			Logger.getLogger(EmployeeXSquad.class.getName()).log(Level.SEVERE, null, ex);
		}
		return employeeId;
	}

	@Override
	public void update(EmployeeXSquad t) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
		public void delete(int employeeId) {
		try {
			ps = con.prepareStatement("DELETE FROM employeexsquad where employeeid=?");
			ps.setInt(1, employeeId);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(EmployeeXSquad.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
