package com.marketplace.service.impl.cotroller;


import com.marketplace.domain.dto.UserDto;
import com.marketplace.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@Log
@RequestMapping(path = "/registration")
public class RegistrationController {

    public final UserService userService;

    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public String showRegistrationPage(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping()
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return new ModelAndView("registration");
        }

        if (userService.isAlreadyRegistered(userDto)){
            log.info("User is already registered by email: " + userDto.getEmail());
            return new ModelAndView("home",
                    "message",
                    "An account for " + userDto.getEmail() + " already exists.");
        }

        UserDto savedUserDto =  userService.registerUser(userDto);
        return new ModelAndView("login", "message", "Successful registration!");
    }
}