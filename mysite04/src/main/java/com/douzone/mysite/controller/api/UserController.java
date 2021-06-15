package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@RestController("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkemail")
	public JsonResult checkEmail(@RequestParam(value = "email", required = true ,defaultValue = "") String email) {
		UserVo vo = userService.getUser(email);
//		Boolean date = vo !=null;
//		JsonResult result = new JsonResult();
//		JsonResult result = JsonResult.success(vo !=null);
//		JsonResult result = JsonResult.fail("...");
//		result.setResult("ok");
//		result.setDate(date);
		
		return JsonResult.success(vo != null);
	}
}
