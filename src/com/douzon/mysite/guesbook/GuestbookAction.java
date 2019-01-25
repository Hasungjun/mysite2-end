package com.douzon.mysite.guesbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.GuestbookDao;
import com.douzon.mysite.vo.GuestbookVo;

public class GuestbookAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> list = new GuestbookDao().getList();
		int size = list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("size", size);
		WebUtils.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
