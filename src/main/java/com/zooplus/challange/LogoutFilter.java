package com.zooplus.challange;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zooplus.challange.util.SessionManager;

// Implements Filter class
public class LogoutFilter implements Filter {
	private List<String> excludedURLs;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {

			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			HttpSession session = request.getSession(false);
			boolean isExcludedURL = false;
			for (int i = 0; i < excludedURLs.size(); i++) {
				if (request.getRequestURL().indexOf(excludedURLs.get(i)) > -1) {
					isExcludedURL = true;
					break;
				}
			}
			if (isExcludedURL) {
				chain.doFilter(request, response);
			} else {
				if (session == null || !SessionManager.getInstance().isValidSession(session.getId())) {
					if (session != null)
						session.invalidate();
					response.sendRedirect(request.getContextPath() + "/login");
					response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
					response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
					response.setDateHeader("Expires", 0);
				} else {
					chain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String[] excluded = filterConfig.getInitParameter("excludedURLs").split(";");
		this.excludedURLs = new ArrayList<String>();
		for (int i = 0; i < excluded.length; i++) {
			excludedURLs.add(excluded[i]);
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}
}