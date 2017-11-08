package com.doggy.groupId.doggy.module.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping("test")
	public String test(Model model) throws IOException {
		model.addAttribute("k", "v");
		return "index";
	}
}
