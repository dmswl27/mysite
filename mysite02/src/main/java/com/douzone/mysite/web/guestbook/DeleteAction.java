package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.util.MvcUtils;
import com.douzone.mvc.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no")); 
		String password = request.getParameter("password");
		System.out.println(no + "  "+ password );
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassward(password);
		
		new GuestbookRepository().delete(vo);
		MvcUtils.redirect(request.getContextPath() + "/guestbook", request, response);
		
	}

}
