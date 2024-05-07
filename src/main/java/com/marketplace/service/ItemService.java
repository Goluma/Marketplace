package com.marketplace.service;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface ItemService {
    ItemDto save(ItemDto itemDto, UserDetailsImpl userDetails);
}
