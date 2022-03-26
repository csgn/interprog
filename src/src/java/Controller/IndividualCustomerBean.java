/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.IndividualCustomerDAO;
import Model.IndividualCustomer;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Aykut
 */
@Named(value = "individualCustomerBean")
@SessionScoped
public class IndividualCustomerBean implements Serializable {

	private IndividualCustomerDAO dao;
	private IndividualCustomer model;
	
	public IndividualCustomerBean() {
		model = new IndividualCustomer();
		dao = new IndividualCustomerDAO();
	}
	
	public IndividualCustomer find(int id) {
		return dao.find(id);
	}

	public List<IndividualCustomer> findAll() {
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
	
	public IndividualCustomerDAO getDao() {
		return dao;
	}

	public void setDao(IndividualCustomerDAO dao) {
		this.dao = dao;
	}

	public IndividualCustomer getModel() {
		return model;
	}

	public void setModel(IndividualCustomer model) {
		this.model = model;
	}
	
	public void clearModel() {
		model = new IndividualCustomer();
	}

	public void editForm(IndividualCustomer e) {
		model = e;
	}
	
}
