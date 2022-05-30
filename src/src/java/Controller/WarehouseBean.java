package Controller;

import DAO.WarehouseDAO;
import Model.Warehouse;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "warehouseBean")
@SessionScoped
public class WarehouseBean implements Serializable {

	private Warehouse model;
	private final WarehouseDAO dao;

	public WarehouseBean() {
		model = new Warehouse();
		dao = new WarehouseDAO();
	}

	public Warehouse find(int id) {
		return dao.find(id);
	}

	public List<Warehouse> findAll() {
		return dao.findAll();
	}

	public List<Warehouse> findAllLimit() {
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

	public Warehouse getModel() {
		return model;
	}

	public void clearModel() {
		model = new Warehouse();
	}

	public void editForm(Warehouse w) {
		model = w;
	}
}
