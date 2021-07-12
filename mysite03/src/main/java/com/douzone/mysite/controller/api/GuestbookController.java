package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;


@RestController("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping("")
	public JsonResult list(@RequestParam("no") Long no) {
		List<GuestbookVo> list = new ArrayList<>(); 
		list = guestbookService.getMessageList(no);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
	@PostMapping("")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return JsonResult.success(vo);
	}
	
	@GetMapping("/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no, 
			@RequestParam(value="password", required=true, defaultValue="") String password) {
		Boolean result;
		result = guestbookService.deleteMessage(no, password);
		String r;
		if(result) {
			r = no.toString();
		} else {
			r = "false";
		}
		return JsonResult.success(r);		
	}
	
}
