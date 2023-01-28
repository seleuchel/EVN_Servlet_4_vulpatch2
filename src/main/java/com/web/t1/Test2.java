package com.web.t1;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/test2")
public class Test2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		//req.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		
		// POST 
		String str = req.getParameter("p1"); //string - method 지정안하면 데이터가 위에 노출.(ex. 토큰 등 )
		String str2 = req.getParameter("p2"); //string2 - method 지정안하면 위에 노출.(ex. 토큰 등)
		out.println("p1 : "+str);
		out.println("p2 : "+str2);

		
		out.close();
		
	}

}
