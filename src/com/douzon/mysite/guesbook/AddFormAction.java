package com.douzon.mysite.guesbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.GuestbookDao;
import com.douzon.mysite.vo.GuestbookVo;
import com.douzon.mysite.vo.UserDao;
import com.douzon.mysite.vo.UserVo;

public class AddFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String content = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(pass);
		vo.setMessage(content);
		
		new GuestbookDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/guestbook");
	}

}
