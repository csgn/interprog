/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package Utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author csgn
 */
@Named("navigation")
@RequestScoped
public class Navigation implements Serializable {
	private String content = "/modules/home.xhtml";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
