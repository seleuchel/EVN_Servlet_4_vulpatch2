package com.web.t1.filter;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public final class RequestWrapper extends HttpServletRequestWrapper {
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter); //? who call
		if (values == null) {
			return null;
		}
		
		int cnt = values.length;
		String[] encodedValues = new String[cnt];
		for (int i = 0 ; i < cnt ; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
		
	}
	@Override
	public String getParameter(String parameter) { // who call
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
		
	}
	
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}
	
	private String cleanXSS(String value) {
		StringBuffer buf = null;
		String[] checkStrlist = {
			"script", "javascript","iframe","onerror", "xss", "onclick", "vbscript", "img", "marquee","obejct"	
		};
		
		for(String checkstr:checkStrlist) {
			while(value.indexOf(checkstr) != -1) {
				value = value.replace(checkstr, "");
			}
			
			while(value.toLowerCase().indexOf(checkstr) != -1) {
				buf = new StringBuffer(value);
				buf = buf.replace(value.toLowerCase().indexOf(checkstr), value.toLowerCase().indexOf(checkstr) + checkstr.length(), "");
				value = buf.toString();
				
			}
		}
		value = value.replaceAll("<",  "&lt;").replaceAll(">",  "&gt;");
		value = value.replaceAll("script", "");
		value = value.replaceAll("javascript", "");
		value = value.replaceAll("alert", "");
		value = value.replaceAll("/", "");
		value = value.replaceAll("\\\\", "");
		return value;
		
	}
}
