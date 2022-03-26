package Controller;

import DAO.EmployeeXSquadDAO;
import Model.EmployeeXSquad;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "employeeXSquadBean")
@SessionScoped
public class EmployeeXSquadBean implements Serializable {

	private EmployeeXSquadDAO employeeXSquadDAO;
	private EmployeeXSquad employeeXSquadModel;
	private List<EmployeeXSquad> employeeXSquadList;
	
	public EmployeeXSquadBean() {
	}

	public EmployeeXSquadDAO getEmployeeXSquadDAO() {
		if(this.employeeXSquadDAO == null){
			this.employeeXSquadDAO = new EmployeeXSquadDAO();
		}
		return employeeXSquadDAO;
	}

	public void setEmployeeXSquadDAO(EmployeeXSquadDAO employeeXSquadDAO) {
		this.employeeXSquadDAO = employeeXSquadDAO;
	}

	public EmployeeXSquad getEmployeeXSquadModel() {
		if(employeeXSquadModel == null){
			this.employeeXSquadModel = new EmployeeXSquad();
	}
		return employeeXSquadModel;
	}

	public void setEmployeeXSquadModel(EmployeeXSquad employeeXSquadModel) {
		this.employeeXSquadModel = employeeXSquadModel;
	}

	public List<EmployeeXSquad> getEmployeeXSquadList() {
		return employeeXSquadList;
	}

	public void setEmployeeXSquadList(List<EmployeeXSquad> employeeXSquadList) {
		this.employeeXSquadList = employeeXSquadList;
	}
	
	
}
