package Controller;

import DAO.CustomerTypeDAO;
import Model.CustomerType;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value= "customerTypeBean")
@SessionScoped
public class CustomerTypeBean implements Serializable {
	
	private CustomerTypeDAO dao;
	private CustomerType model;

	public CustomerTypeBean() {
		model = new CustomerType();
		dao = new CustomerTypeDAO();
	}
	
	public CustomerType find(int id){
		return dao.find(id);
	}
	
	public List<CustomerType> findAll(){
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
	
	public CustomerTypeDAO getDao() {
		if (this.dao == null){
			this.dao = new CustomerTypeDAO();
		}
		return dao;
	}

	public CustomerType getModel() {
		if (this.model == null){
			this.model = new CustomerType();
		}
		return model;
	}

	public void clearModel() {
		model = new CustomerType();
	}

	public void editForm(CustomerType a) {
		model = a;
	}
}
