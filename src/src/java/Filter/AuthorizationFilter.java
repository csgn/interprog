package Filter;

import jakarta.faces.application.ResourceHandler;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Metin
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	private boolean isLoggedIn(HttpSession session) {
		if (session == null) {
			return false;
		} else {
			return session.getAttribute("phoneNumber") != null;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession(false);
			String reqURI = req.getRequestURI();
			String loginPath = "/login";
			
			// True if logged in otherwise false
			boolean loginStatus = isLoggedIn(session);
			boolean loginRequest = reqURI.contains(loginPath);
			boolean resourceRequest = reqURI.contains(ResourceHandler.RESOURCE_IDENTIFIER);

			if (resourceRequest) {
				chain.doFilter(request, response);
			
				// request to other pages but not logged in
			} else if (!loginRequest && !loginStatus) {
				res.sendRedirect(loginPath);
			
				// request to login page and logged in redirect to index
			} else if (loginRequest && loginStatus){
				res.sendRedirect("/");
			} else {
				chain.doFilter(request, response);
			}
		} catch (ServletException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
}
