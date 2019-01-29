package com.douzon.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.BoardDao;
import com.douzon.mysite.vo.BoardVo;


public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		List<BoardVo> list = new BoardDao().select(vo);
		
		
		

		request.setAttribute("list", list);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
