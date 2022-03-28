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

	public EmployeeBean() {
		model = new Employee();
		dao = new EmployeeDAO();
	}

	public Employee find(int id) {
		return dao.find(id);
	}

	public List<Employee> findAll() {
		return dao.findAll();
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

}
