package Controller;

import DAO.EmployeeXSquadDAO;
import Model.EmployeeXSquad;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "employeeXSquadBean")
@SessionScoped
public class EmployeeXSquadBean implements Serializable {

	private EmployeeXSquadDAO dao;
	private EmployeeXSquad model;
	
	public EmployeeXSquadBean() {
		dao = new EmployeeXSquadDAO();
		model = new EmployeeXSquad();
	}

	public EmployeeXSquadDAO getDao() {	
		return dao;
	}

	public EmployeeXSquad getModel() {
		return model;
	}
	
	public EmployeeXSquad find(int id) {
		return dao.find(id);
	}

	public List<EmployeeXSquad> findAll() {
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
		model = new EmployeeXSquad();
	}

	public void editForm(EmployeeXSquad e) {
		model = e;
	}
}
