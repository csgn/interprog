package Controller;

import DAO.JobXEmployeeDAO;
import Model.JobXEmployee;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "jobXEmployeeBean")
@SessionScoped
public class JobXEmployeeBean implements Serializable {

	private JobXEmployeeDAO dao;
	private JobXEmployee model;
	

	public JobXEmployeeBean() {
		dao = new JobXEmployeeDAO();
		model = new JobXEmployee();
	}

	public JobXEmployeeDAO getDao() {
		return dao;
	}

	public JobXEmployee getModel() {
		return model;
	}
	
	public JobXEmployee find(int id) {
		return dao.find(id);
	}

	public List<JobXEmployee> findAll() {
		return dao.findAll();
	}

	public void create() {
		int jobId = dao.create(model);
		this.clearModel();

		System.out.println("CREATED ID: " + jobId);
	}

	public void delete(int jobId) {
		dao.delete(jobId);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}
	
	public void clearModel() {
		model = new JobXEmployee();
	}

	public void editForm(JobXEmployee j) {
		model = j;
	}
}
