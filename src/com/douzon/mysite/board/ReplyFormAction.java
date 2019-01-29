package com.douzon.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int no = Integer.parseInt(request.getParameter("no"));

		request.setAttribute("no", no);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/reply.jsp");
	}

}
