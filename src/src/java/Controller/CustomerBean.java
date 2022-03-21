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
@Named(value = "customerBean")
@SessionScoped
public class CustomerBean implements Serializable {

	private CustomerDAO dao;
	private Customer entity;
	private List<Customer> customerList;

	public CustomerDAO getDao() {
		if (this.dao == null) {
			this.dao = new CustomerDAO();
		}
		return dao;
	}

	public void setDao(CustomerDAO dao) {
		this.dao = dao;
	}

	public Customer getEntity() {
		if (this.entity == null) {
			this.entity = new Customer();
		}
		return entity;
	}

	public void setEntity(Customer entity) {
		this.entity = entity;
	}

	public List<Customer> getCustomerList() {
		this.customerList = dao.findAll();
		return this.customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public CustomerBean() {
	}

}
