package com.bit.jblog.intercepter;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit.jblog.vo.MemberVo;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class AuthIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");
		if (session == null || authUser == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return false;
		}

		String[] path = request.getRequestURI().split("/");
		String ownerId = null;
		if(request.getContextPath().length() > 0) {
			ownerId = path[2];
		}else {
			ownerId = path[1];
		}
		
		System.out.println(ownerId);
		System.out.println(authUser.toString());

		if (ownerId == null || !((authUser.getId()).equals(ownerId))) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

		return true;

	}

}
