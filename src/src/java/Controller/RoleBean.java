package Controller;

import DAO.RoleDAO;
import Model.Role;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "roleBean")
@SessionScoped
public class RoleBean implements Serializable{

	private Role model;
	private final RoleDAO dao;
	
	public RoleBean() {
		dao = new RoleDAO();
		model = new Role();
	}

	public Role find(int id) {
		return dao.find(id);
	}

	public List<Role> findAll() {
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

	public Role getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Role();
	}

	public void editForm(Role r) {
		model = r;
	}
}
