/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Squad;
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
public class SquadDAO {
	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private Squad tmp;
	private List<Squad> squads;
	
	public List<Squad> findAll(){
		
		squads = new ArrayList<>();
		try{
			this.ps = this.con.prepareStatement("Select * from squad");
			rs = ps.executeQuery();
				while(rs.next()){
					tmp = new Squad(
								rs.getInt("id"),
								rs.getString("name"));
				squads.add(tmp);
				}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return squads;
	}
	
	public Squad findById(int id){
		
		try{
			this.ps = this.con.prepareStatement("Select * from squad where id =?");
			this.ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				tmp = new Squad(
								rs.getInt("id"),
								rs.getString("name"));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return tmp;
	}
	
	public Squad findByName(String name){
		
		try{
			this.ps = this.con.prepareStatement("Select * from squad where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				tmp = new Squad(
								rs.getInt("id"),
								rs.getString("name"));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return tmp;
	}
}
