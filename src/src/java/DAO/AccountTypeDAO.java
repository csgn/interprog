/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.AccountType;
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


public class AccountTypeDAO {
	
	private final Connection con = PGConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	private AccountType tmp;
	private List<AccountType> accountTypes; 
	
	public List<AccountType> findAll(){
		
		try{
				accountTypes = new ArrayList<>();
				this.ps = this.con.prepareStatement("Select * from accounttype");
				rs = ps.executeQuery();
				while(rs.next()){
					tmp = new AccountType(
								rs.getInt("id"),
								rs.getString("name"));
				accountTypes.add(tmp);
				}			
		}catch(SQLException e){
				System.out.println(e.getMessage());
		}
		return accountTypes;
	}
	
	public AccountType findByName(String name){
		try{
			this.ps = this.con.prepareStatement("Select * from accounttype where name =?");
			this.ps.setString(1, name);
			rs = ps.executeQuery();		
			while(rs.next()){
				tmp = new AccountType(
								rs.getInt("id"),							
								rs.getString("name"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return tmp;
	}
}
