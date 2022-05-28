package Controller;

import DAO.EmployeeDAO;
import Model.Employee;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 * @author Sergen
 */
@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {
	private Employee model;
	private final EmployeeDAO dao;


	private int page=1;
	private int pageSize=3;
	private int pageCount;

	public EmployeeBean() {
		model = new Employee();
		dao = new EmployeeDAO();
	}

	public Employee find(int id) {
		return dao.find(id);
	}

	public List<Employee> findAll() {
		return dao.findAll(page, pageSize);
	}

	public void create() {
		int id = dao.create(model);

		if (id == -1) return;

		for (var s : model.getEmployeeSquads())
			dao.createEmployeeSquad(id, s.getId());

		for (var w : model.getEmployeeWarehouses())
			dao.createEmployeeWarehouse(id, w.getId());

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

	public Employee getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Employee();
	}

	public void editForm(Employee e) {
		model = e;
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
		if (page == pageCount)
			page = 1;
		else
			page++;
	}

	public void previous() {
		if (page == 1)
			page = pageCount;
		else
			page--;
	}


}
