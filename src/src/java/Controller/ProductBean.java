/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author Aykut
 */
@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable {

	/**
	 * Creates a new instance of ProductBean
	 */
	public ProductBean() {
	}
	
}
