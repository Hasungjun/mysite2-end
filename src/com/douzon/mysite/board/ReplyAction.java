package com.douzon.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.BoardDao;
import com.douzon.mysite.vo.BoardVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		
		new BoardDao().reply(vo,no);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board");
	}

}
