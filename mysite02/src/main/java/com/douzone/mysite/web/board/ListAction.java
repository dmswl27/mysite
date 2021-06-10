package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalCount = new BoardRepository().getBoardCount(); 
		int page;
		if(request.getParameter("page")==null) {
			page=1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		int countBoard= 10;										  							// 글 10개 뽑음
		int countPage = 5;		
		int totalPage = (int)Math.ceil((double)totalCount/countBoard) ; 							// 전체 페이지 개수
		int startCount = (page - 1) * countBoard;  	
		int endCount = page * countBoard;   					  							// 쿼리 limit에서 사용할 endCount      ex) limit startCount, endCount
		int firstPageNo = (((int) ((double) page / 10 + 0.9)) - 1) * countBoard + 1;		// 첫 페이지 번호
		int lastPageNo = totalPage;															// 마지막 페이지 번호
		int currentPageNo = page; 															// 현재 페이지
		if (lastPageNo > firstPageNo + 10 - 1) { 											// 마지막 페이지 번호를 설정해줌 
			lastPageNo = firstPageNo + 10 - 1;
		}
		
		List<BoardVo> list = new BoardRepository().getPager(startCount, countBoard);
		
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startCount", startCount);
		map.put("endCount", endCount);
		map.put("firstPageNo", firstPageNo);
		map.put("lastPageNo", lastPageNo);
		map.put("currentPageNo", currentPageNo);
		map.put("countPage", countPage);
		map.put("countBoard", countBoard);
		map.put("currentPageNo", currentPageNo);
		map.put("page", page);
		System.out.println(map);
		
		request.setAttribute("list", list);
		request.setAttribute("map", map);
		
		MvcUtils.forward("board/list", request, response);
		
		
		

	}

}
