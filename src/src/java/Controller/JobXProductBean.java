/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.JobXProductDAO;
import Model.JobXProduct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "jobXProductBean")
@SessionScoped
public class JobXProductBean {
		
	private JobXProductDAO dao;
	private JobXProduct model;

	public JobXProductBean() {
		model = new JobXProduct();
		dao = new JobXProductDAO();
	}

	public JobXProductDAO getDao() {
		return dao;
	}

	public JobXProduct getModel() {
		return model;
	}
	
	public JobXProduct find(int jobId) {
		return dao.find(jobId);
	}

	public List<JobXProduct> findAll() {
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
	
	public void clearModel() {
		model = new JobXProduct();
	}

	public void editForm(JobXProduct e) {
		model = e;
	}
}
