package com.marketplace.cotroller;


import com.marketplace.domain.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/registration")
public class RegistrationController {

    @GetMapping(path = "/")
    public String showRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }
}
