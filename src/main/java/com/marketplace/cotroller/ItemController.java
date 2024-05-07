package com.marketplace.cotroller;

import com.marketplace.config.ImageConverter;
import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import com.marketplace.service.ItemService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@Log
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(path = "/item")
    public String showItemPage(){
        return "item";
    }

    @GetMapping(path = "/creation")
    public String showCreationPage(Model model){
        ItemDto itemDto = new ItemDto();
        model.addAttribute("item", itemDto);
        return "creation";
    }

    @PostMapping(path = "/creation")
    public ModelAndView saveItem(@ModelAttribute("item") @Valid ItemDto itemDto,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        if (bindingResult.hasErrors()){
            return new ModelAndView("creation", "message", "Error");
        }
        log.info("Size: " + itemDto.getImage().length + " " + itemDto.getDescription());
        ImageConverter.byteArrayToImage(itemDto.getImage(), "D:/");
        //itemService.save(itemDto, userDetails);
        return new ModelAndView("creation", "message", "Item successfully created");
    }

    @GetMapping(path = "/show")
    public ModelAndView showAllItemsPage(){
        return new ModelAndView("show");
    }

    @GetMapping(path = "/update")
    public ModelAndView showUpdatePage(){
        return new ModelAndView("update");
    }

}
