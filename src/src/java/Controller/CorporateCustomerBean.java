/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.CorporateCustomerDAO;
import Model.CorporateCustomer;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Aykut
 */
@Named(value = "corporateCustomerBean")
@SessionScoped
public class CorporateCustomerBean implements Serializable{
	
	private CorporateCustomerDAO dao;
	private CorporateCustomer model;
	
	public CorporateCustomerBean() {
		model = new CorporateCustomer();
		dao = new CorporateCustomerDAO();
	}
	
	public CorporateCustomer find(int id) {
		return dao.find(id);
	}

	public List<CorporateCustomer> findAll() {
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
	
	public CorporateCustomerDAO getDao() {
		return dao;
	}

	public CorporateCustomer getModel() {
		return model;
	}

	public void clearModel() {
		model = new CorporateCustomer();
	}

	public void editForm(CorporateCustomer e) {
		model = e;
	}
}
