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

	private IndividualCustomerDAO individualCustomerDAO;
	private IndividualCustomer individualCustomerModel;
	private List<IndividualCustomer> individualCustomerList;

	public IndividualCustomerBean() {
	}

	public IndividualCustomerDAO getIndividualCustomerDAO() {
		if(this.individualCustomerDAO == null){
			this.individualCustomerDAO = new IndividualCustomerDAO();
		}
		return individualCustomerDAO;
	}

	public void setIndividualCustomerDAO(IndividualCustomerDAO individualCustomerDAO) {
		this.individualCustomerDAO = individualCustomerDAO;
	}

	public IndividualCustomer getIndividualCustomerModel() {
		if(this.individualCustomerModel == null){
			this.individualCustomerModel = new IndividualCustomer();
		}
		return individualCustomerModel;
	}

	public void setIndividualCustomerModel(IndividualCustomer individualCustomerModel) {
		this.individualCustomerModel = individualCustomerModel;
	}

	public List<IndividualCustomer> getIndividualCustomerList() {
		return individualCustomerList;
	}

	public void setIndividualCustomerList(List<IndividualCustomer> individualCustomerList) {
		this.individualCustomerList = individualCustomerList;
	}
	
}
