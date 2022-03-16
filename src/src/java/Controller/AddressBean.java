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

	private AddressDAO addressDAO;
	private Address entity;
	private List<Address> addressList;

	public AddressBean() {
	}

	public AddressDAO getDao() {
		if(this.addressDAO == null){
			this.addressDAO = new AddressDAO();
		}
		return addressDAO;
	}

	public void setDao(AddressDAO dao) {
		this.addressDAO = dao;
	}

	public Address getEntity() {
		if(this.entity==null){
			this.entity = new Address();
		}
		return entity;
	}

	public void setEntity(Address entity) {
		this.entity = entity;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
	
	
}
