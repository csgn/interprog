/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.core.JobDAO;
import Model.Job;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {

	private Job employee;
	private JobDAO dao;

	public JobBean() {
		employee = new Job();
		dao = new JobDAO();
	}

	public Job find(int id) {
		return dao.find(id);
	}

	public List<Job> findAll() {
		return dao.findAll();
	}

	public int create() {
		int id = dao.create(employee);
		employee = new Job();

		return id;
	}

	public Job getJob() {
		return employee;
	}
}
