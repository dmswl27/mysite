package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;


@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	GalleryService galleryService;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.findAll();
		model.addAttribute("list",list);
		return "gallery/index";
	}
	
	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.delete(no);
		return "redirect:/gallery";
	}
	
	@Auth
	@RequestMapping("/upload")
	public String update(MultipartFile file){
		String url = fileUploadService.restore(file);
		galleryService.uploadImage(url);
		
		return "redirect:/gallery";
	}

}
