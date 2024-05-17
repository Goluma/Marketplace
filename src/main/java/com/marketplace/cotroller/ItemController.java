package com.marketplace.cotroller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

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
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (bindingResult.hasErrors()){
            return new ModelAndView("creation", "message", "Error");
        }

        itemService.save(itemDto, userDetails);
        return new ModelAndView("creation", "message", "Item successfully created");
    }

    @GetMapping(path = "/show")
    public Model showAllUserItemsItemsPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        model.addAttribute("items", itemDtoList);
        return model;
    }

    @GetMapping(path = "/update")
    public Model showUpdatePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        model.addAttribute("items", itemDtoList);
        return model;
    }

    @PostMapping(path = "/update")
    public String updateItem(@ModelAttribute("selectedItem") UUID oldItemDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.setUpdatedItemUuid(oldItemDto);
        return "redirect:/updateNew";
    }

    @GetMapping(path = "/updateNew")
    public Model updatedItemCreationPage(Model model){
        model.addAttribute("item", new ItemDto());
        return model;
    }

    @PostMapping(path = "/updateNew")
    public ModelAndView updatedItemCreation(@ModelAttribute("item") @Valid ItemDto itemDto,
                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("updateNew", "message", "Error");
        }
        itemService.updateItem(itemDto);
        return new ModelAndView("redirect:/update");
    }

    @GetMapping(path = "/delete")
    public Model showDeletePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        model.addAttribute("items", itemDtoList);
        return model;
    }

    @PostMapping(path = "/delete")
    public String deleteItem(@RequestParam("selectedItems") List<UUID> selectedItems){
        itemService.deleteItem(selectedItems);
        return "redirect:/delete";
    }

}

