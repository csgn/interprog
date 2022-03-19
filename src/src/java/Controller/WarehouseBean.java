/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.WarehouseDAO;
import Model.Warehouse;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "warehouseBean")
@SessionScoped
public class WarehouseBean implements Serializable{

	private WarehouseDAO warehouseDAO;
	private Warehouse warehouseModel;
	private List<Warehouse> warehouseList;
	
	public WarehouseBean() {
	}

	public WarehouseDAO getWarehouseDAO() {
		if(this.warehouseDAO == null){
			this.warehouseDAO = new WarehouseDAO();
		}
		return warehouseDAO;
	}

	public void setWarehouseDAO(WarehouseDAO warehouseDAO) {
		this.warehouseDAO = warehouseDAO;
	}

	public Warehouse getWarehouseModel() {
		if(this.warehouseModel == null){
			this.warehouseModel = new Warehouse();
		}
		return warehouseModel;
	}

	public void setWarehouseModel(Warehouse warehouseModel) {
		this.warehouseModel = warehouseModel;
	}

	public List<Warehouse> getWarehouseList() {
		return warehouseList;
	}

	public void setWarehouseList(List<Warehouse> warehouseList) {
		this.warehouseList = warehouseList;
	}
}
