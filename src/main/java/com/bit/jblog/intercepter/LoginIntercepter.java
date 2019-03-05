package com.bit.jblog.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit.jblog.service.MemberService;
import com.bit.jblog.vo.MemberVo;

public class LoginIntercepter extends HandlerInterceptorAdapter {

	
	@Autowired
	MemberService service;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
				
		if(id != null && pwd !=null) {
			
			MemberVo memberVo = new MemberVo();
			memberVo.setId(id);
			memberVo.setPassword(pwd);
			
			memberVo = service.getMember(memberVo);
			if(memberVo != null) {
				request.getSession(true).setAttribute("authUser", memberVo);
			}
			
		}
		
	
	}

	
	
}
