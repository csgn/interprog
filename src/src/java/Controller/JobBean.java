package Controller;

import DAO.EmployeeDAO;
import DAO.JobDAO;
import DAO.ProductDAO;
import Model.Employee;
import Model.Job;
import Model.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 * @author Sergen
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {
	private Job model;
	private final JobDAO dao;

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
	
		if (id == -1) return;

		for (Employee e : model.getJobEmployees()) {
			dao.createJobEmployee(id, e.getId());
		}

		for (Product p : model.getJobProducts()) {
			dao.createJobProduct(id, p.getId());
		}

		this.clearModel();
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

	public void clearModel() {
		model = new Job();
	}

	public void editForm(Job j) {
		model = j;
	}

}
