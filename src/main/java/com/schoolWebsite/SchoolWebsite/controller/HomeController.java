package com.schoolWebsite.SchoolWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"/","","/home"})
    public String getHome(Model model)
    {
        model.addAttribute("username","hemanth");
        return "index.html";
    }
}
