package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutentication implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		String URL = httpRequest.getServletPath();
		if (!verifyPermittedUrls(URL)) {
			HttpSession session = httpRequest.getSession();
			session.setMaxInactiveInterval(15 * 60);
			httpRequest.getSession().setAttribute("requestedUrl", httpRequest.getServletPath());
			if (session.getAttribute("user") == null) {
				httpResponse.setStatus(httpResponse.SC_MOVED_TEMPORARILY);
				httpResponse.setHeader("Location", httpRequest.getContextPath() + "/index.jsp");
				return;
			}
		}

		chain.doFilter(request, response);

	}

	private boolean verifyPermittedUrls(String URL) {
		String[] permittedRegex = { ".*\\/index[\\.]?.*", ".*\\/LoginServlet[\\.]?.*",
				"[\\/]?resources(\\/\\w+)*(\\..*)?" };
		for (String regex : permittedRegex) {
			if (URL.matches(regex)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
