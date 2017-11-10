package com.duiya.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.duiya.model.User;


public class LoginInterceptor implements HandlerInterceptor{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			if (user == null && ((request.getRequestURI().contains("/uorder/"))
					||(request.getRequestURI().contains("/ushopcar/")))) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write("{\"code\": -1,\"msg\":\"用户未登录\",\"data\": \"\"}");
				return false;
			}
			return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
	public void hotFun(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<Integer> list = (List<Integer>) session.getAttribute("list");
		//判断是当前用户是否有这个
		if(list.indexOf(1) != -1) {
			//执行添加
		}
	}

}
