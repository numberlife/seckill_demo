package com.qjmoneynode.seckilldemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {

    /*
     * @description
     * @author qjmoneynode 
     * @date 2023/7/10 16:00
     * @param model
     * @return String
     **/
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","xxxx");
        return "hello";
    }
}
