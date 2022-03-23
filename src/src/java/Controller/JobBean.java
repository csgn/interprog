/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.JobDAO;
import Model.Customer;
import Model.Employee;
import Model.Job;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aykut
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {

	private Job job;
	private JobDAO dao;

	private Customer customer;
	private Employee employee;
	private String description;
	private String dateTime;

	public JobBean() {
		dao = new JobDAO();
	}

	public Job find(int id) {
		return dao.find(id);
	}

	public List<Job> findAll() {
		return dao.findAll();
	}

	public void create() {
		try {
			Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateTime);
			job.setDescription(description);
			job.setDate(date);
			job.setFiles(new ArrayList<>());

			int id = dao.create(job);
			System.out.println("id: " + id);

		} catch (ParseException ex) {
			Logger.getLogger(JobBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Job getJob() {
		return job;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	


}
