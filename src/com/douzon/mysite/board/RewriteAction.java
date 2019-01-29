package com.douzon.mysite.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.BoardDao;
import com.douzon.mysite.vo.BoardVo;

public class RewriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String user_name = request.getParameter("name");
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		vo.setUser_name(user_name);
		vo.setNo(no);
		
		new BoardDao().update(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board");
	}

}
