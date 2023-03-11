package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {
    @RequestMapping(value = "/")
    public String getHomepage(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("This is an index page");
        model.addAttribute("messages", messages);
        return "index";
    }

}
