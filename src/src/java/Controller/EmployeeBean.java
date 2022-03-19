/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.EmployeeDAO;
import Model.Employee;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

	private EmployeeDAO employeeDAO;
	private Employee employeeModel;
	private List<Employee> employeeList;
	
	public EmployeeBean() {
	}

	public EmployeeDAO getEmployeeDAO() {
		if(this.employeeDAO == null){
			this.employeeDAO = new EmployeeDAO();
		}
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public Employee getEmployeeModel() {
		if(this.employeeModel == null){
			this.employeeModel = new Employee();
		}
		return employeeModel;
	}

	public void setEmployeeModel(Employee employeeModel) {
		this.employeeModel = employeeModel;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	
}
