package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근경로(인증이 필요한 접근에 ㄱ=대한 체크)
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo auther = (UserVo)session.getAttribute("authUser");
		if(auther ==null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;		
		}
		
		Long userNo = auther.getNo();
		UserVo userVo = new UserRepository().findByNo(userNo);
		request.setAttribute("userVo", userVo);
		MvcUtils.forward("board/write", request, response);
	}

}
