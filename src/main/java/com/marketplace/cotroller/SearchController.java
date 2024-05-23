package com.marketplace.cotroller;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import com.marketplace.service.ItemService;
import com.marketplace.service.impl.RequestLimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(path = "/search")
public class SearchController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RequestLimiterService requestLimiterService;

    @GetMapping
    public String searchPage(Model model){
        ItemDto itemDto = new ItemDto();
        model.addAttribute("item", itemDto);
        return "search";
    }

    @PostMapping
    public ModelAndView searching(@ModelAttribute("item") ItemDto searchRequest, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (!requestLimiterService.addNewRequest(userDetails)){
            String str = String.format("Need %d seconds to wait. Too much requests", requestLimiterService.secondsToWait(userDetails.getUsername()));
            return new ModelAndView("search",
                    "messages", str);
        }
        List<ItemDto> resultList = itemService.getAllItemsByRequest(searchRequest.getName(), userDetails);
        if (resultList.size() == 0){
            return new ModelAndView("search", "message", "No such items");
        }
        return new ModelAndView("searchResults", "items", resultList);
    }
}
