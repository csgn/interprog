package Controller;

import DAO.StatusDAO;
import Model.Status;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Metin
 */

@Named("statusBean")
@SessionScoped
public class StatusBean implements Serializable {
	private Status model;
	private final StatusDAO dao;
	
	public StatusBean() {
		model = new Status();
		dao = new StatusDAO();
	}
	
	public Status find(int id) {
		return dao.find(id);
	}

	public List<Status> findAll() {
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

	public Status getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Status();
	}

	public void editForm(Status s) {
		model = s;
	}
}
