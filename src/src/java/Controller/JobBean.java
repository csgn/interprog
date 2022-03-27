package Controller;

import DAO.JobDAO;
import Model.Customer;
import Model.Employee;
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

	private Job model;
	private JobDAO dao;
	private Customer customer;
	private Employee employee;

	public JobBean() {
		dao = new JobDAO();
		model = new Job();
	}

	public Job find(int id) {
		return dao.find(id);
	}

	public List<Job> findAll() {
		return dao.findAll();
	}

	public void create() {
			int id = dao.create(model);
			System.out.println("id: " + id);
	}
	
	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}

	public Job getModel() {
		return model;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void clearModel() {
		model = new Job();
	}
	
	public void editForm(Job j) {
		model = j;
	}
}
