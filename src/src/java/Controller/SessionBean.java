package Controller;

import DAO.EmployeeDAO;
import Model.Employee;
import Utils.Session;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
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

	private String phoneNumber;
	private String password;
	private String sessionId;
	private Employee employee;
	private HttpSession sessionCreated;
	private final EmployeeDAO employeeDAO;

	public SessionBean() {
		this.employeeDAO = new EmployeeDAO();
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

	public Employee getEmployee() {
		return employee;
	}

	// is user exist with given username and password
	public boolean validateUsernamePassword() {
		return this.employeeDAO.validate(this.phoneNumber, this.password);
	}

	// if user exist create new session
	public HttpSession createSession() {
		if (!validateUsernamePassword()) {
			return null;
		}
		
		// find user
		this.employee = this.employeeDAO.findByPhoneNumber(this.phoneNumber);
		
		// generate new session
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		// attributes are currently not used
		session.setAttribute("phoneNumber", this.phoneNumber);
		session.setAttribute("roleId", this.employee.getRoleId());
		session.setAttribute("name", this.employee.getName());
		session.setAttribute("surname", this.employee.getSurname());
		this.sessionId = session.getId();
		return session;
	}

	public String login() {
		this.sessionCreated = createSession();
		
		// redirect index page if login successful otherwise login page
		return this.sessionCreated == null ? "login" : "dashboard";
	}

	public String logout() {
		HttpSession session = Session.getSession();
		if (session != null) {
			session.invalidate();
		}
		
		// redirect login page after logging out
		return "login";
	}
}
