package Utils;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Metin
 */
public class Session {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getPhoneNumber() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("phoneNumber").toString();
	}

	public static String getName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("name").toString();
	}

	public static String getSurname() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("surname").toString();
	}

	public static String getFullName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("name").toString() + session.getAttribute("surname").toString();
	}

	public static String getRoleId() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("roleId").toString();
	}

	public static String getId() {
		HttpSession session = getSession();
		return session.getId();
	}

	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null) {
			return (String) session.getAttribute("userid");
		} else {
			return null;
		}
	}
}
