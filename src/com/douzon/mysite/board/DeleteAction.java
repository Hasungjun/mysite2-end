package com.douzon.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.BoardDao;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int no = Integer.parseInt(request.getParameter("no")) ;
	

			new BoardDao().delete(no);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board");
	}

}
