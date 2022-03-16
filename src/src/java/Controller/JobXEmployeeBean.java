/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.JobXEmployeeDAO;
import Model.JobXEmployee;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "jobXEmployeeBean")
@SessionScoped
public class JobXEmployeeBean {

	private JobXEmployeeDAO jobXEmployeeDAO;
	private JobXEmployee jobXEmployeeModel;
	private List<JobXEmployee> jobXEmployeeList;
	
	public JobXEmployeeBean() {
	}

	public JobXEmployeeDAO getJobXEmployeeDAO() {
		if(this.jobXEmployeeDAO == null){
			this.jobXEmployeeDAO = new JobXEmployeeDAO();
		}
		return jobXEmployeeDAO;
	}

	public void setJobXEmployeeDAO(JobXEmployeeDAO jobXEmployeeDAO) {
		this.jobXEmployeeDAO = jobXEmployeeDAO;
	}

	public JobXEmployee getJobXEmployeeModel() {
		if(this.jobXEmployeeModel == null){
			this.jobXEmployeeModel = new JobXEmployee();
		}
		return jobXEmployeeModel;
	}

	public void setJobXEmployeeModel(JobXEmployee jobXEmployeeModel) {
		this.jobXEmployeeModel = jobXEmployeeModel;
	}

	public List<JobXEmployee> getJobXEmployeeList() {
		return jobXEmployeeList;
	}

	public void setJobXEmployeeList(List<JobXEmployee> jobXEmployeeList) {
		this.jobXEmployeeList = jobXEmployeeList;
	}
	
	
}
