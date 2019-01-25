package com.douzon.mysite.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.UserDao;
import com.douzon.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authuser = new UserDao().get(email, password);

		if(authuser ==null) {
			//인증실패
			request.setAttribute("result", "fail");
			WebUtils.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		// 인증성공 -> 인증처리
		HttpSession session = request.getSession(true);
		
		session.setAttribute("authuser", authuser);
	
		//main redirect
		WebUtils.redirect(request, response, request.getContextPath());
		
	
	}

}
