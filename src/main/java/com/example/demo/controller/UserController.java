package com.example.demo.controller;

import com.example.demo.service.UserServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceIn userService;

    @Autowired
    public UserController(UserServiceIn userService) {
        this.userService = userService;
    }

    //1
    @GetMapping
    public String getUserInfo(Model model1, Authentication authentication) {
        model1.addAttribute("user", userService.loadUserByUsername(authentication.getName()));
        return "user_info";
    }
}