/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.EmployeeXWarehouseDAO;
import Model.EmployeeXWarehouse;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "employeeXWarehouseBean")
@SessionScoped
public class EmployeeXWarehouseBean implements Serializable {

	private EmployeeXWarehouseDAO employeeXWarehouseDAO;
	private EmployeeXWarehouse employeeXWarehouseModel;
	private List<EmployeeXWarehouse> employeeXWarehouseList;
	

	public EmployeeXWarehouseBean() {
	}

	public EmployeeXWarehouseDAO getEmployeeXWarehouseDAO() {
		if(this.employeeXWarehouseDAO == null){
			this.employeeXWarehouseDAO = new EmployeeXWarehouseDAO();
		}
		return employeeXWarehouseDAO;
	}

	public void setEmployeeXWarehouseDAO(EmployeeXWarehouseDAO employeeXWarehouseDAO) {
		this.employeeXWarehouseDAO = employeeXWarehouseDAO;
	}

	public EmployeeXWarehouse getEmployeeXWarehouseModel() {
		if(this.employeeXWarehouseModel == null){
			this.employeeXWarehouseModel = new EmployeeXWarehouse();
		}
		return employeeXWarehouseModel;
	}

	public void setEmployeeXWarehouseModel(EmployeeXWarehouse employeeXWarehouseModel) {
		this.employeeXWarehouseModel = employeeXWarehouseModel;
	}

	public List<EmployeeXWarehouse> getEmployeeXWarehouseList() {
		return employeeXWarehouseList;
	}

	public void setEmployeeXWarehouseList(List<EmployeeXWarehouse> employeeXWarehouseList) {
		this.employeeXWarehouseList = employeeXWarehouseList;
	}
	
	
}
