/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.AddressDAO;
import Model.Address;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "addressBean")
@SessionScoped
public class AddressBean implements Serializable {

	private AddressDAO dao;
	private Address model;

	public AddressBean() {
		model = new Address();
		dao = new AddressDAO();
	}
	
	public Address find(int id) {
		return dao.find(id);
	}

	public List<Address> findAll() {
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

	public AddressDAO getDao() {
		if(this.dao == null){
			this.dao = new AddressDAO();
		}
		return dao;
	}

	public Address getModel() {
		if(this.model==null){
			this.model = new Address();
		}
		return model;
	}
	
	public void clearModel() {
		model = new Address();
	}

	public void editForm(Address e) {
		model = e;
	}
}
