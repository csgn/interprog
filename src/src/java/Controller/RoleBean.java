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
	private int page = 1;
	private int pageSize = 3;
	private int pageCount;
	
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

	public List<Role> findAllLimit() {
		return dao.findAll(page,pageSize);
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
