package ru.vorobev;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(urlPatterns = "")
public class HelloServlet implements Servlet {
	
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.getWriter().write("<h1>HelloWorld</h1>");
	}
	
	@Override
	public String getServletInfo() {
		return null;
	}
	
	@Override
	public void destroy() {
	
	}
}
