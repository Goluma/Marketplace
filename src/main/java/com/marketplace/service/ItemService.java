package com.marketplace.service;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    ItemDto save(ItemDto itemDto, UserDetailsImpl userDetails);

    List<ItemDto> getAllUsersItems(UserDetailsImpl userDetails);

    void deleteItem(List<UUID> selectedItems, UserDetailsImpl userDetails);

    void setUpdatedItemUuid(UUID oldItemDto, UserDetailsImpl userDetails);

    void updateItem(ItemDto itemDto, UserDetailsImpl userDetails);

    List<ItemDto> getAllItemsByRequest(String searchRequest, UserDetailsImpl userDetails);
}
