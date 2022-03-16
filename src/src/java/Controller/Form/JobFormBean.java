package Controller.Form;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.Serializable;

/**
 *
 * @author csgn
 */
@Named(value = "jobFormBean")
@SessionScoped
public class JobFormBean implements Serializable {
	private String customer;
	private String text;
	private String dateTime;
	private String employee;
	private Part file;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}


	public void create() {
		System.out.println("LISTENING");
	}
}
