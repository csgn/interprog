package Utils;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Metin
 */
public class Session {

	// returns the current session if one doesn't exist returns null
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static String getId() {
		HttpSession session = getSession();
		return session.getId();
	}
}
