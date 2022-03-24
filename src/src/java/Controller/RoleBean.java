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

	private RoleDAO dao;
	private Role model;
	
	public RoleBean() {
		dao = new RoleDAO();
	}

	public Role getModel() {
		return model;
	}

	public void create() {
	}

	public void delete() {

	}

	public void update() {

	}

	public List<Role> findAll() {
		return dao.findAll();
	}

	
}
