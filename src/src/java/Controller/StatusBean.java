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
	private int page = 1;
	private int pageSize = 3;
	private int pageCount;
	
	public StatusBean() {
		model = new Status();
		dao = new StatusDAO();
	}
	
	public Status find(int id) {
		return dao.find(id);
	}

	public List<Status> findAll() {
		return dao.findAll(page,pageSize);
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

	public Status getModel() {
		return model;
	}
	
	public void clearModel() {
		model = new Status();
	}

	public void editForm(Status s) {
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
