package com.marketplace.service;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ItemService {
    ItemDto save(ItemDto itemDto, UserDetailsImpl userDetails);

    List<ItemDto> getAllUsersItems(UserDetailsImpl userDetails);
}
