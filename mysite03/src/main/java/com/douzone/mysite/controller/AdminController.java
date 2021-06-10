package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminservice;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = new SiteVo();
		vo = adminservice.findAll();
		model.addAttribute("siteVo",vo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String updateMain(@RequestParam(value = "title", required=true, defaultValue="") String title ,
			@RequestParam(value = "welcome", required=true, defaultValue="") String welcome ,
			@RequestParam("file1") MultipartFile file1,
			@RequestParam(value = "description", required=true, defaultValue="") String description, 
			Model model) {
		String url = fileUploadService.restore(file1);
		SiteVo vo = new SiteVo();
		vo.setTitle(title);
		vo.setWelcome(welcome);
		vo.setProfile(url);
		vo.setDescription(description);
		System.out.println(vo);
		adminservice.update(vo);
		model.addAttribute("siteVo",vo);
		
		
		
		return "redirect:/admin";
	}
	
	
	

	@RequestMapping("/guestbook")
	public String guestbook() {

		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {

		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {

		return "admin/user";
	}

}
