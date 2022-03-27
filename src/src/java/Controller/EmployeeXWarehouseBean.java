package Controller;

import DAO.EmployeeXWarehouseDAO;
import Model.EmployeeXWarehouse;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "employeeXWarehouseBean")
@SessionScoped
public class EmployeeXWarehouseBean implements Serializable {

	private EmployeeXWarehouseDAO dao;
	private EmployeeXWarehouse model;

	public EmployeeXWarehouseDAO getDao() {
		return dao;
	}

	public EmployeeXWarehouse getModel() {
		return model;
	}
	
	public EmployeeXWarehouseBean() {
		dao = new EmployeeXWarehouseDAO();
		model = new EmployeeXWarehouse();
	}
	public EmployeeXWarehouse find(int id) {
		return dao.find(id);
	}

	public List<EmployeeXWarehouse> findAll() {
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
		model = new EmployeeXWarehouse();
	}

	public void editForm(EmployeeXWarehouse e) {
		model = e;
	}
}
