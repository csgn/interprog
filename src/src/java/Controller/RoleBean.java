/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.RoleDAO;
import Model.Role;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "roleBean")
@SessionScoped
public class RoleBean implements Serializable{

	private RoleDAO roleDAO;
	private Role roleModel;
	private List<Role> roleList;
	
	public RoleBean() {
	}

	public RoleDAO getRoleDAO() {
		if(this.roleDAO == null){
			this.roleDAO = new RoleDAO();
		}
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Role getRoleModel() {
		if(this.roleModel == null){
			this.roleModel = new Role();
		}
		return roleModel;
	}

	public void setRoleModel(Role roleModel) {
		this.roleModel = roleModel;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	
	
}
