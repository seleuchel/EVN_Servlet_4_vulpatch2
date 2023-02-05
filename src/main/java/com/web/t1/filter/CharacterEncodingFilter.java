package com.web.t1.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		System.out.println("before fileter");
		arg0.setCharacterEncoding("UTF-8");
		HttpServletResponse httpResponse = (HttpServletResponse) arg1;
	    httpResponse.setHeader("Content-Security-Policy", "default-src 'self'");	
		
		//arg1.setHeader("Cache-Control","no-cache");
		arg2.doFilter(arg0, arg1);
		System.out.println("after fileter");
		
		
	}

}
