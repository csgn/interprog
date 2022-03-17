/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.SquadDAO;
import Model.Squad;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "squadBean")
@SessionScoped
public class SquadBean implements Serializable{

	private SquadDAO squadDAO;
	private Squad squadModel;
	private List<Squad> squadList;
	
	public SquadBean() {
	}

	public SquadDAO getSquadDAO() {
		if(this.squadDAO == null){
			this.squadDAO = new SquadDAO();
		}
		return squadDAO;
	}

	public void setSquadDAO(SquadDAO squadDAO) {
		this.squadDAO = squadDAO;
	}

	public Squad getSquadModel() {
		if(this.squadModel == null){
			this.squadModel = new Squad();
		}
		return squadModel;
	}

	public void setSquadModel(Squad squadModel) {
		this.squadModel = squadModel;
	}

	public List<Squad> getSquadList() {
		return squadList;
	}

	public void setSquadList(List<Squad> squadList) {
		this.squadList = squadList;
	}
	
	
}
