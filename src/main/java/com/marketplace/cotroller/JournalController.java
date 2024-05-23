package com.marketplace.cotroller;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import com.marketplace.domain.dto.JournalDto;
import com.marketplace.domain.dto.UserDto;
import com.marketplace.service.JournalService;
import com.marketplace.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Log
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showAdminPage(){
        return "admin";
    }

    @GetMapping(path = "/journal")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String journalPage(Model model){
        JournalDto journalDto = new JournalDto();
        model.addAttribute("journal", journalDto);
        return "journal";
    }

    @PostMapping(path = "/journal")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView journalResult(@ModelAttribute("journal") JournalDto searchRequest){

        List<JournalDto> statsList = journalService.getCountStatistic(Integer.parseInt(searchRequest.getTableName()));
        return new ModelAndView("journalResult", "journal", statsList);
    }

    @GetMapping(path = "/allUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Model showAllUsers(Model model){
        List<UserDto> userDtoList = userService.getAllUsers();
        model.addAttribute("users", userDtoList);
        return model;
    }

    @GetMapping(path = "/specificUserStatistic")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String specificUserChoice(Model model){
        UserDto itemDto = new UserDto();
        model.addAttribute("user", itemDto);
        return "specificUserStatistic";
    }

    @PostMapping(path = "/specificUserStatistic")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView searching(@ModelAttribute("user") ItemDto searchRequest){
        List<JournalDto> resultList = journalService.getAllRecordsByUserUuid(searchRequest.getUserUuid());
        if (resultList.size() == 0){
            return new ModelAndView("specificUserStatistic", "message", "No such user");
        }
        return new ModelAndView("specificUserStatisticResult", "users", resultList);
    }
}
