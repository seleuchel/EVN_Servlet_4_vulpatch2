package com.web.t1;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Cookie;
@WebServlet("/cookie2")
public class Cookie2 extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		Cookie[] cookie_arr = req.getCookies();
		
		String result = "";
		
		PrintWriter out = res.getWriter();
		
		// POST 
		String str = req.getParameter("v"); //string - method 지정안하면 데이터가 위에 노출.(ex. 토큰 등 )
		String str2 = req.getParameter("sb");
		
		// set cookie
		Cookie vCookie = new Cookie("v", str);
		Cookie sbCookie = new Cookie("sb", str2);
		vCookie.setPath("/cookie2"); //쿠키 경로 고정 - 해당 주소 요청시에만 전달
		vCookie.setMaxAge(24*60*60);
		sbCookie.setPath("/cookie2"); //쿠키 경로 고정 - 해당 주소 요청시에만 전달
		
		// 상태 저장
		if(str2.equals("쿠키저장")) {
			res.addCookie(vCookie);
			res.addCookie(sbCookie);
			res.setHeader("Cache-Control", "no-store");
			res.setHeader("Pragma", "no-cache");
		}else {
			
			
		if(req.getProtocol().equals("HTTP/1.1"))
			res.setHeader("Cache-Control", "no-cache");
	
		String cc1 = "";
		
		for(Cookie c :cookie_arr) {
			if(c.getName().equals("v")) {
				str = c.getValue();
				out.println("현재저장된 쿠키 v : "+str);
			}
		}

		for(Cookie c :cookie_arr) {
			if(c.getName().equals("sb")) {
				str2 = c.getValue();
				out.println("현재저장된 쿠키 sb : "+str2);
			}
		}
		
		
		
		}
		
		out.close();
		
	}

}
