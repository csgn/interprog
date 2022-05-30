package Controller;

import DAO.JobDAO;
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

	private int page = 1;
	private int pageSize = 3;
	private int pageCount;


	public JobBean() {
		dao = new JobDAO();
		model = new Job();
	}

	public Job find(int id) {
		return dao.find(id);
	}


	public List<Job> findAll() {
		return dao.findAll(page, pageSize);
	}

	public List<Job> findAllLimit() {
		return dao.findAll(page, pageSize);
	}


	public void create() {
		int id = dao.create(model);

		if (id == -1) {
			return;
		}

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		this.pageCount = (int) Math.ceil(this.dao.count() / (double) pageSize);
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void next() {
		if (page == pageCount) {
			page = 1;
		} else {
			page++;
		}
	}

	public void previous() {
		if (page == 1) {
			page = pageCount;
		} else {
			page--;
		}
	}

}
