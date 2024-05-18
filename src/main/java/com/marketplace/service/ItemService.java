package com.marketplace.service;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    ItemDto save(ItemDto itemDto, UserDetailsImpl userDetails);

    List<ItemDto> getAllUsersItems(UserDetailsImpl userDetails);

    void deleteItem(List<UUID> selectedItems);

    void setUpdatedItemUuid(UUID oldItemDto);

    void updateItem(ItemDto itemDto);

    List<ItemDto> getAllItemsByRequest(String searchRequest);
}
