package com.doggy.groupId.doggy.module.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author doggy
 * Created on 2017-08-08.
 */
@Controller
@RequestMapping
public class TestController {
    @RequestMapping()
    public String toIndex(Model model){
        model.addAttribute("k", "v");
        return "index";
    }
}
