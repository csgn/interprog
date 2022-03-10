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
 */
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
			HttpSession ses = req.getSession(false);
			String reqURI = req.getRequestURI();

			if (!reqURI.contains("/login.xhtml") && (ses != null && ses.getAttribute("phoneNumber") != null)) {
				chain.doFilter(request, response);
			} else if (reqURI.contains("/login.xhtml") && (ses != null && ses.getAttribute("phoneNumber") != null)) {
				res.sendRedirect(req.getContextPath() + "index.xhtml");
			} else if (reqURI.contains("/login.xhtml") && ses == null) {
				chain.doFilter(request, response);
			} else if (!reqURI.contains("/login.xhtml") && ses == null) {
				res.sendRedirect(req.getContextPath() + "login.xhtml");
			} else if (reqURI.contains("/login.xhtml") && (ses != null && ses.getAttribute("phoneNumber") == null)) {
				chain.doFilter(request, response);
			} else if (!reqURI.contains("/login.xhtml") && (ses != null && ses.getAttribute("phoneNumber") == null)) {
				res.sendRedirect(req.getContextPath() + "login.xhtml");
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
