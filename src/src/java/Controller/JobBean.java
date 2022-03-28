package Controller;

import DAO.EmployeeDAO;
import DAO.JobDAO;
import DAO.ProductDAO;
import Model.Employee;
import Model.Job;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.time.LocalDate.now;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aykut
 * @author Sergen
 */
@Named("jobBean")
@SessionScoped
public class JobBean implements Serializable {
	private List<Job> modelList;

	private Job model;
	private JobDAO dao;

	private EmployeeDAO employeeDAO;
	private ProductDAO productDAO;

	public JobBean() {
		dao = new JobDAO();
		model = new Job();
		employeeDAO = new EmployeeDAO();
		productDAO = new ProductDAO();
	}

	public Job find(int id) {
		return dao.find(id);
	}

	public List<Job> findAll() {
		if (modelList == null)
			modelList = dao.findAll();
		return modelList;
	}

	public void create() {
		int id = dao.create(model);
	
		if (id == -1) return;

		for (Employee e : model.getJobEmployees()) {
			employeeDAO.createJobEmployee(id, e.getId());
		}
	}

	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);

		employeeDAO.updateJobEmployee(model.getId(), model.getJobEmployees());
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
