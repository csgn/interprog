/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;
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
public class RoleDAO {

	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Role tmp;
	private List<Role> roles;

	public List<Role> findAll() {

		roles = new ArrayList<>();
		try {
			this.ps = this.con.prepareStatement("Select * from role");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Role(
								rs.getInt("id"),
								rs.getString("name"));
				roles.add(tmp);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return roles;
	}

	public Role findById(int id) {

		try {
			this.ps = this.con.prepareStatement("Select * fRoleole where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Role(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}

	public Role findByName(String name) {

		try {
			this.ps = this.con.prepareStatement("Select * from role where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Role(
								rs.getInt("id"),
								rs.getString("name"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public void insert(int id, String name){
		
		try{
			this.ps= this.con.prepareStatement("insert into role values (id = ?, name = ?)");
			this.ps.setInt(1, id);
			this.ps.setString(2, name);
			rs = ps.executeQuery();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void deleteById(int id){
		
		try{
			this.ps= this.con.prepareStatement("delete from role where (id = ?)");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
