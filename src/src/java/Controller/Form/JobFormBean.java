package Controller.Form;

import Model.Customer;
import Model.Employee;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author csgn
 */
@Named("jobFormBean")
@SessionScoped
public class JobFormBean implements Serializable {

	private String customer;
	private Employee employee;
	private String description;
	private Date dateTime;

	public JobFormBean() {
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public void create() {
		System.out.println("CREATED");
		System.out.println(employee);
	}

}
