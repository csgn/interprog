/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

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

	public CustomerBean() {
		model = new Customer();
		dao = new CustomerDAO();
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

	
	public void clearModel() {
		model = new Customer();
	}

	public void editForm(Customer e) {
		model = e;
	}
}
