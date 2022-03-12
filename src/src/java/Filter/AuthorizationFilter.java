package Filter;

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
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
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

			// request to other pages and already logged in
			if (!reqURI.contains("login") && (session != null && session.getAttribute("phoneNumber") != null)) {
				chain.doFilter(request, response);

				// request to login page and already logged in -> return index page
			} else if (reqURI.contains("login") && (session != null && session.getAttribute("phoneNumber") != null)) {
				res.sendRedirect(req.getContextPath() + "index.xhtml");

				// request to login page and not logged in
			} else if (reqURI.contains("login") && session == null) {
				chain.doFilter(request, response);

				// request to other pages and not logged in -> return login page
			} else if (!reqURI.contains("login") && session == null) {
				res.sendRedirect(req.getContextPath() + "/login");
				// request to login page and not logged in
			} else if (reqURI.contains("login") && (session != null && session.getAttribute("phoneNumber") == null)) {
				chain.doFilter(request, response);

				// request to other pages and not logged in -> return login page
			} else if (!reqURI.contains("login") && (session != null && session.getAttribute("phoneNumber") == null)) {
				res.sendRedirect(req.getContextPath() + "/login");
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

 */