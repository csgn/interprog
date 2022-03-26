/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.ActivityDAO;
import Model.Activity;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "activityBean")
@SessionScoped
public class ActivityBeanJava {

	private ActivityDAO dao;
	private Activity model;
	
	
	public ActivityBeanJava() {
		model = new Activity();
		dao = new ActivityDAO();
	}
	
	public Activity find(int id){
		return dao.find(id);
	}
	
	public List<Activity> findAll() {
		return dao.findAll();
	}
	
	public void create() {
		int id = dao.create(model);
		this.clearModel();

		System.out.println("CREATED ID: " + id);
	}
	
	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}
	
	public ActivityDAO getDao() {
		if(this.dao == null){
			this.dao = new ActivityDAO();
		}
		return dao;
	}

	public Activity getModel() {
		if(this.model == null){
			this.model = new Activity();
		}
		return model;
	}

	public void clearModel() {
		model = new Activity();
	}

	public void editForm(Activity a) {
		model = a;
	}
}
