/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */

@Named(value= "productBean")
@SessionScoped
public class ProductBean implements Serializable {
		
	private Product model;
	private final ProductDAO dao;
	
	public ProductBean() {
		model = new Product();
		dao = new ProductDAO();
	}
		
	public Product find(int id) {
		return dao.find(id);
	}

	public List<Product> findAll() {
		return dao.findAll();
	}

	public void create() {
		int id = dao.create(model);
		this.clearModel();
	}

	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}

	public Product getModel() {
		return model;
	}

	public ProductDAO getDao() {
		return dao;
	}
	
	public void clearModel() {
		model = new Product();
	}

	public void editForm(Product p) {
		model = p;
	}
}

