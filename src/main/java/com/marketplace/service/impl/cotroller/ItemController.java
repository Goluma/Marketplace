package com.marketplace.service.impl.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/item")
public class ItemController {

    @GetMapping()
    public String showItemPage(){
        return "item";
    }

    @GetMapping("/creation")
    public String showCreationPage(){
        return "creation";
    }

    @GetMapping("/show")
    public ModelAndView showAllItemsPage(){
        return new ModelAndView("show");
    }

    @GetMapping("/update")
    public ModelAndView showUpdatePage(){
        return new ModelAndView("update");
    }

}
