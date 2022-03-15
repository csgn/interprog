/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Warehouse;
import Utils.PGConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aykut
 */
public class WarehouseDAO {
	
	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Warehouse tmp;
	private List<Warehouse> warehouses;
	
	public List<Warehouse> findAll(){
		
		warehouses = new ArrayList<>();
		try{
			this.ps = this.con.prepareStatement("Select * from warehouse");
			rs = ps.executeQuery();
				while(rs.next()){
					tmp = new Warehouse(
								rs.getInt("id"),
								rs.getString("name"));
				warehouses.add(tmp);
				}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return warehouses;
	}
	
	public Warehouse findById(int id){
		
		try{
			this.ps = this.con.prepareStatement("Select * from warehouse where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				tmp = new Warehouse(
								rs.getInt("id"),
								rs.getString("name"));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public Warehouse findByName(String name){
		
		try{
			this.ps = this.con.prepareStatement("Select * from warehouse where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				tmp = new Warehouse(
								rs.getInt("id"),
								rs.getString("name"));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return tmp;
	}
}
