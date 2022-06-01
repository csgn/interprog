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
	private int page = 1;
	private int pageSize = 3;
	private int pageCount;


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
		return dao.findAll(page, pageSize);
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
