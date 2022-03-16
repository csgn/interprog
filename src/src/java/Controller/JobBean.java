/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.JobDAO;
import Model.Job;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "jobBean")
@SessionScoped
public class JobBean implements Serializable{

	private JobDAO jobDAO;
	private Job jobModel;
	private List<Job> jobList;
	
	public JobBean() {
	}

	public JobDAO getJobDAO() {
		if(this.jobDAO == null){
			this.jobDAO = new JobDAO();
		}
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public Job getJobModel() {
		if(this.jobModel == null){
			this.jobModel = new Job();
		}
		return jobModel;
	}

	public void setJobModel(Job jobModel) {
		this.jobModel = jobModel;
	}

	public List<Job> getJobList() {
		return jobList;
	}

	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	
}
