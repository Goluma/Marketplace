package com.marketplace.cotroller;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import com.marketplace.service.ItemService;
import com.marketplace.service.impl.RequestLimiterService;
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

    @Autowired
    private RequestLimiterService requestLimiterService;

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

        if (!requestLimiterService.addNewRequest(userDetails)){
            return new ModelAndView("creation",
                    "message",
                    String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername())));
        }

        itemService.save(itemDto, userDetails);
        return new ModelAndView("creation", "message", "Item successfully created");
    }

    @GetMapping(path = "/show")
    public ModelAndView showAllUserItemsItemsPage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("show",
                    "message", str);
        }
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        return new ModelAndView("show", "items", itemDtoList);
    }

    @GetMapping(path = "/update")
    public ModelAndView showUpdatePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("update",
                    "messages", str);
        }
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        return new ModelAndView("update", "items", itemDtoList);
    }

    @PostMapping(path = "/update")
    public String updateItem(@ModelAttribute("selectedItem") UUID oldItemDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        itemService.setUpdatedItemUuid(oldItemDto, userDetails);
        return "redirect:/updateNew";
    }

    @GetMapping(path = "/updateNew")
    public Model updatedItemCreationPage(Model model){
        model.addAttribute("item", new ItemDto());
        return model;
    }

    @PostMapping(path = "/updateNew")
    public ModelAndView updatedItemCreation(@ModelAttribute("item") @Valid ItemDto itemDto,
                                      BindingResult bindingResult,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (bindingResult.hasErrors()){
            return new ModelAndView("updateNew", "message", "Error");
        }

        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("updateNew",
                    "messages", str);
        }
        itemService.updateItem(itemDto, userDetails);
        return new ModelAndView("redirect:/update");
    }

    @GetMapping(path = "/delete")
    public ModelAndView showDeletePage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("delete",
                    "messages", str);
        }
        List<ItemDto> itemDtoList = itemService.getAllUsersItems(userDetails);
        return new ModelAndView("delete", "items", itemDtoList);
    }

    @PostMapping(path = "/delete")
    public ModelAndView deleteItem(@RequestParam("selectedItems") List<UUID> selectedItems,
                             @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("delete",
                    "messages", str);
        }
        itemService.deleteItem(selectedItems, userDetails);
        return new ModelAndView("redirect:/delete");
    }

}

