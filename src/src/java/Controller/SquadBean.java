package Controller;

import DAO.SquadDAO;
import Model.Squad;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "squadBean")
@SessionScoped
public class SquadBean implements Serializable{

	private Squad model;
	private final SquadDAO dao;
	
	public SquadBean() {
		model = new Squad();
		dao = new SquadDAO();
	}

	public Squad find(int id) {
		return dao.find(id);
	}

	public List<Squad> findAll() {
		return dao.findAll();
	}

	public void create() {
		int id = dao.create(model);
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

	public Squad getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Squad();
	}

	public void editForm(Squad s) {
		model = s;
	}
}
