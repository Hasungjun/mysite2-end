package com.douzon.mysite.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.BoardDao;
import com.douzon.mysite.vo.BoardVo;


public class BoardAction implements Action {
	public static final int size = 5;
	public static final int block = 5;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int pg = 1;
		
		if(request.getParameter("pg")!=null) {
			pg = Integer.parseInt(request.getParameter("pg"));
			if(pg ==0)
				pg = 1;
		}
		
		int start = (pg*size) - (size-1);
		int end = (pg*size);
		
		int allPage = 0;
		
		int startPage = ((pg-1)/block*block) + 1;
		int endPage = ((pg-1)/block*block) + block;

		List<BoardVo> list2 = new BoardDao().getList();
		int boardsize = list2.size() - (pg-1) * size;
		int boardsize2 = (list2.size()+1) - (pg-1) * size;
		
		
		if(boardsize < 0) {
			boardsize = 0;
		}
		
		List<BoardVo> list = new BoardDao().getListPage(pg,size);
		allPage = (int)Math.ceil(boardsize/(double)size);

		if(allPage==0) {
			pg = (list2.size()-1)/size + 1;
			
			 start = (pg*size) - (size-1);
			 end = (pg*size);
			
			 allPage = 0;
			
			 startPage = ((pg-1)/block*block) + 1;
			 endPage = ((pg-1)/block*block) + block;

			 list2 = new BoardDao().getList();
			 boardsize = list2.size() - (pg-1) * size;
			 boardsize2 = (list2.size()+1) - (pg-1) * size;
			if(boardsize < 0) {
				boardsize = 0;
			}
			
		 list = new BoardDao().getListPage(pg,size);
			
			
		}
		
		
		request.setAttribute("start",start);
		request.setAttribute("end",end);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("allPage",allPage);
		request.setAttribute("pg", pg);
		request.setAttribute("list", list);
		request.setAttribute("size", boardsize2);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
	}

}
