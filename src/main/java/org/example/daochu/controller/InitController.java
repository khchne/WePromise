package org.example.daochu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
    @RequestMapping("/")
    public String init() {
        return "redirect:/startPoint1.html";
    }
}
