package com.doggy.groupId.doggy.module.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
@Controller
@RequestMapping("/")
public class TestController {
	@RequestMapping("upload")
	public String upload(@RequestPart("file") MultipartFile file, Model model) {
		model.addAttribute("fileName", file.getOriginalFilename());
		return "upload-success";
	}
	
	@RequestMapping("error")
	public String error() {
		throw new RuntimeException();
	}
}
