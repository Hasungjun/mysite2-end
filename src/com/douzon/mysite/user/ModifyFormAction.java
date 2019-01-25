package com.douzon.mysite.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//접근제어
		UserVo authUser = null;
		
		HttpSession session = request.getSession();
		if(session !=null) {
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser==null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		//UserVo vo = new UserDao().get(authUser.getNo());
		
		WebUtils.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
	
	}

}
