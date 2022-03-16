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
public class CorporateCustomerBean implements Serializable {

	private CorporateCustomerDAO dao;
	private CorporateCustomer entity;
	private List<CorporateCustomer> corporateCustomerList;

	public CorporateCustomerDAO getDao() {
		if(this.dao == null){
			this.dao = new CorporateCustomerDAO();
		}
		return dao;
	}

	public void setDao(CorporateCustomerDAO dao) {
		this.dao = dao;
	}

	public CorporateCustomer getEntity() {
		if(this.entity == null){
			this.entity = new CorporateCustomer();
		}
		return entity;
	}

	public void setEntity(CorporateCustomer entity) {
		this.entity = entity;
	}

	public List<CorporateCustomer> getCorporateCustomerList() {
		return corporateCustomerList;
	}

	public void setCorporateCustomerList(List<CorporateCustomer> corporateCustomerList) {
		this.corporateCustomerList = corporateCustomerList;
	}
	
	public CorporateCustomerBean() {
	}
}
