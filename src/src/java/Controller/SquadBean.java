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
	private int page = 1;
	private int pageSize = 3;
	private int pageCount;
	
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

	public List<Squad> findAllLimit() {
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

	public Squad getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Squad();
	}

	public void editForm(Squad s) {
		model = s;
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
