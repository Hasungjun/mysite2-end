package com.douzon.mysite.guesbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.GuestbookDao;
import com.douzon.mysite.vo.GuestbookVo;


public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 String nono = request.getParameter("no");
		 String password = request.getParameter("password");
		 long no = Long.parseLong(nono);
	
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(no);
			vo.setPassword(password);
			new GuestbookDao().delete(vo);
			

			WebUtils.redirect(request, response, request.getContextPath()+"/guestbook");
	}

}
