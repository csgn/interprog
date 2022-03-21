/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.core.EmployeeDAO;
import Model.Employee;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named("employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {
	private Employee employee;
	private EmployeeDAO dao;

	public EmployeeBean() {
		employee = new Employee();
		dao = new EmployeeDAO();
	}

	public Employee find(int id) {
		return dao.find(id);
	}

	public List<Employee> findAll() {
		return dao.findAll();
	}

	public int create() {
		int id = dao.create(employee);
		employee = new Employee();

		return id;
	}

	public Employee getEmployee() {
		return employee;
	}


}
