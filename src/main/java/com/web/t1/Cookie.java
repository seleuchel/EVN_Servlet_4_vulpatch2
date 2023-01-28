package com.web.t1;

import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/cookie")
public class Cookie extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		//req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		PrintWriter out = res.getWriter();
		
		// POST 
		String str = req.getParameter("v"); //string - method 지정안하면 데이터가 위에 노출.(ex. 토큰 등 )
		String str2 = req.getParameter("sb");
		
		// 상태 저장
		if(str2.equals("1번누름")) {
		session.setAttribute("v", str);
		session.setAttribute("sb", str2);
		}else if(str2.equals("2번누름")) {
		session.setAttribute("v", str);
		session.setAttribute("sb", str2);
		}else {
		
		String str3 = (String) session.getAttribute("v");
		String str4 = (String) session.getAttribute("sb");

		out.println("현재저장 v1 : "+str);
		out.println("현재저장 지금누름 : "+str2);
		
		out.println("이전저장 v1 : "+str3);
		out.println("이전저장 지금누름 : "+str4);
		}
		
		out.close();
		
	}

}
