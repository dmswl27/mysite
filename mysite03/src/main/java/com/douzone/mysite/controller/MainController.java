package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private AdminService adminservice;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("")
	public String index(Model model) {
		SiteVo vo = new SiteVo();
		vo = adminservice.findAll();
		model.addAttribute("siteVo", vo);

		return "main/index";
	}

	@ResponseBody
	@RequestMapping("/msg")
	public UserVo msg(@AuthUser UserVo vo) {

		return vo;
	}
}