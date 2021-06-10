package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.util.MvcUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = (String)request.getParameter("no");
		BoardVo vo = new BoardRepository().findByNo(Long.parseLong(no));
		new BoardRepository().updatehit(Long.parseLong(no));
		
		request.setAttribute("board", vo);
		request.setAttribute("no", Long.parseLong(no));
		MvcUtils.forward("board/view", request, response);
	}

}
