package com.douzon.mysite.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.UserVo;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		if(session ==null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		// logout 처리
	 UserVo authUser = (UserVo)session.getAttribute("authuser");
	 if(authUser==null) {
		WebUtils.redirect(request, response, request.getContextPath());
		return;
	 }
	 
	 
	 session.removeAttribute("authuser");
	 session.invalidate();
	 
	 WebUtils.redirect(request, response, request.getContextPath());
	 
	}

}
