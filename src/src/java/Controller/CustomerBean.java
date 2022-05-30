package Controller;

import DAO.AddressDAO;
import DAO.CustomerDAO;
import Model.Customer;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named("customerBean")
@SessionScoped
public class CustomerBean implements Serializable {
		private Customer model;
		private CustomerDAO dao;
		private AddressDAO addressDAO;
		
		private int page=1;
		private int pageSize=3;
		private int pageCount;

	public CustomerBean() {
		model = new Customer();
		dao = new CustomerDAO();
		addressDAO = new AddressDAO();
	}

	public Customer getModel() {
		return model;
	}

	public CustomerDAO getDao() {
		return dao;
	}
	public Customer find(int id) {
		return dao.find(id);
	}

	public List<Customer> findAll() {
		return dao.findAll();
	}

	public List<Customer> findAllLimit() {
		return dao.findAll(page, pageSize);
	}


	public void create() {
		int addressId = addressDAO.create(model.getAddress());
		int modelId = -1;

		if (addressId != -1) {
			model.getAddress().setId(addressId);
			modelId = dao.create(model);
		}

		this.clearModel();
	}

	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		addressDAO.update(model.getAddress());
		dao.update(model);
		this.clearModel();
	}

	public void clearModel() {
		model = new Customer();
	}

	public void editForm(Customer e) {
		model = e;
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
