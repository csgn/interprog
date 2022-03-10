package Controller;

import DAO.EmployeeDAO;
import Entity.Employee;
import Utils.Session;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author Metin
 */

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	private int roleId;
	private String name;
	private String surname;
	private String phoneNumber;
	private String password;
	private String sessionId;
	private Employee employee;
	private HttpSession sessionCreated;
	private final EmployeeDAO employeeDAO = new EmployeeDAO();

	public SessionBean() {
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public HttpSession getSessionCreated() {
		return sessionCreated;
	}

	public void setSessionCreated(HttpSession sessionCreated) {
		this.sessionCreated = sessionCreated;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public boolean validateUsernamePassword() {
		boolean valid = this.employeeDAO.validate(this.phoneNumber, this.password);
		return valid;
	}
	
	public HttpSession createSession() {
		if (validateUsernamePassword()) {
			this.employee = this.employeeDAO.findByPhoneNumber(this.phoneNumber);
			HttpSession session = Session.getSession();
			session.setAttribute("phoneNumber", this.phoneNumber);
			session.setAttribute("roleId", this.employee.getRoleId());
			session.setAttribute("name", this.employee.getName());
			session.setAttribute("surname", this.employee.getSurname());
			session.setAttribute("sessionId", Session.getId());
			this.sessionId = Session.getId();
			return session;
		} else {
			return null;
		}
	}
	
	public String login() {
		this.sessionCreated = createSession();
		if (this.sessionCreated == null) {
			return "login" + "?faces-redirect=true";
		} else {
			return "index" + "?faces-redirect=true";
		}
	}

	public String logout() {
		HttpSession session = Session.getSession();
		session.invalidate();
		return "login" + "?faces-redirect=true";
	}
}