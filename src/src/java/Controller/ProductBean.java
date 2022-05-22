package Controller;

import DAO.ProductDAO;
import Model.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */

@Named(value= "productBean")
@SessionScoped
public class ProductBean implements Serializable {
	private Product model;
	private final ProductDAO dao;
	
	private int page=1;
	private int pageSize=3;
	private int pageCount;
	
	public ProductBean() {
		model = new Product();
		dao = new ProductDAO();
	}
		
	public Product find(int id) {
		return dao.find(id);
	}

	public List<Product> findAll() {
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

	public Product getModel() {
		return model;
	}

	public ProductDAO getDao() {
		return dao;
	}
	
	public void clearModel() {
		model = new Product();
	}

	public void editForm(Product p) {
		model = p;
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

