package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardservice;

	@RequestMapping("/{page}")
	public String list(
			@PathVariable("page") String pageNum, 
			Model model) {
		int page;

		if (pageNum == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pageNum);
		}

		int totalCount = boardservice.getBoardCount();
		Map<String, Object> map = new HashMap<String, Object>();

		int countBoard = 10; // 글 10개 뽑음
		int countPage = 5;
		int totalPage = (int) Math.ceil((double) totalCount / countBoard); // 전체 페이지 개수
		int startCount = (page - 1) * countBoard;
		int endCount = page * countBoard; // 쿼리 limit에서 사용할 endCount ex) limit startCount, endCount
		int firstPageNo = (((int) ((double) page / 10 + 0.9)) - 1) * countBoard + 1; // 첫 페이지 번호
		int lastPageNo = totalPage; // 마지막 페이지 번호
		int currentPageNo = page; // 현재 페이지
		if (lastPageNo > firstPageNo + 10 - 1) { // 마지막 페이지 번호를 설정해줌
			lastPageNo = firstPageNo + 10 - 1;
		}

		List<BoardVo> list = boardservice.getPager(startCount, countBoard);
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startCount", startCount);
		map.put("endCount", endCount);
		map.put("firstPageNo", firstPageNo);
		map.put("lastPageNo", lastPageNo);
		map.put("currentPageNo", currentPageNo);
		map.put("countPage", countPage);
		map.put("countBoard", countBoard);
		map.put("page", page);

		model.addAttribute("list", list);
		model.addAttribute("map", map);

		return "board/list";
	}
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(
			@RequestParam(value = "title") String title, 
			@RequestParam(value = "contents") String contents,
			@AuthUser UserVo authUser, 
			BoardVo vo) {
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(authUser.getNo());
		boardservice.addMessage(vo);

		return "redirect:/board/1";
	}
	@Auth
	@RequestMapping(value = "/comment/{no}", method = RequestMethod.GET)
	public String comment(
			@PathVariable("no") Long no, 
			Model model) {
		model.addAttribute("no", no);
		System.out.println(no);
		return "board/comment";
	}
	@Auth
	@RequestMapping(value = "/comment/{no}", method = RequestMethod.POST)
	public String comment(
			@PathVariable("no") Long no,
			@RequestParam(value = "title", required=true, defaultValue="") String title,
			@RequestParam(value = "contents", required=true, defaultValue="") String contents, 
			@AuthUser UserVo authUser) {
		BoardVo vo = boardservice.findByRow(no);
		BoardVo v1 = new BoardVo();
		v1.setGroupNo(vo.getGroupNo());
		v1.setOrderNo(vo.getOrderNo()+1);
		v1.setDepth(vo.getDepth()+1);
		v1.setTitle(title);
		v1.setContents(contents);
		v1.setUserNo(authUser.getNo());
		boardservice.InsetAndComment(v1);
		boardservice.insertAdd(v1);

		return "redirect:/board/1";
	}
	@Auth
	@RequestMapping(value = "/modify/{no}")
	public String modify(
			@PathVariable("no") Long no, 
			Model model) {
		BoardVo vo = boardservice.getTitleAndContents(no);
		model.addAttribute("vo", vo);

		return "board/modify";
	}
	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(
			BoardVo vo, 
			Model model, 
			@AuthUser UserVo authUser) {
		vo.setUserNo(authUser.getNo());
		boardservice.updatemessage(vo);
		return "redirect:/board/1";
	}

	@RequestMapping(value = "/view/{no}")
	public String view(
			@PathVariable("no") Long no, 
			Model model) {
		boardservice.updatehit(no);
		BoardVo vo = boardservice.view(no);
		model.addAttribute("vo", vo);
		return "board/view";

	}
	@Auth
	@RequestMapping(value = "delete/{no}")
	public String delete(
			@PathVariable("no") Long no, 
			Model model) {
		boardservice.delete(no);
		return "redirect:/board/1";

	}

}
