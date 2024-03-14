package com.marketplace.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping()
    public String showLoginPage(){
        return "login";
    }

////    @PostMapping()
////    public String showMainPage(@RequestParam Map<String,String> allParams){
////        return "main";
////    }

}
