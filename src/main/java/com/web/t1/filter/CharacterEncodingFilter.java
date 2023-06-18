package com.web.t1.filter;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
	
	public FilterConfig filterConfig;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
 
    public void destroy() {
        this.filterConfig = null;
    }

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("before fileter");
		// before filter
		arg0.setCharacterEncoding("UTF-8");  // important
		// input data
		HttpServletRequest httpRequest = (HttpServletRequest) arg0;
		HttpServletResponse httpResponse = (HttpServletResponse) arg1;
		// No XSS
		//httpResponse.setHeader("Content-Security-Policy", "default-src 'self', script-src 'self'");	// 'none':all fail 'self': local ok
		
		//arg1.setHeader("Cache-Control","no-cache");
		chain.doFilter(new RequestWrapper((HttpServletRequest)arg0), arg1);
		System.out.println("after fileter");
		
		
	}

}
