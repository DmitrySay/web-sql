package servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CurrentFilter implements Filter {

	private boolean status; 
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.status =  Boolean.parseBoolean( filterConfig.getInitParameter("status") );
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		request.setAttribute("status", this.status);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
