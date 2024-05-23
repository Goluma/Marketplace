package com.marketplace.cotroller;

import com.marketplace.service.impl.RequestLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {

    @Autowired
    private RequestLimiterService requestLimiterService;

    @GetMapping("/login")
    public String showLoginPage(){
        requestLimiterService.addUsers();
        return "login";
    }

    @GetMapping(path = "/home")
    public String showHomePage(){
        return "home";
    }

    @GetMapping(path = "/main")
    public String showMainPage(){
        return "main";
    }

}
