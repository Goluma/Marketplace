package com.marketplace.service.impl;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.dto.ItemDto;
import com.marketplace.domain.entity.ItemEntity;
import com.marketplace.mapper.ItemMapper;
import com.marketplace.repository.ItemRepository;
import com.marketplace.service.ItemService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public ItemDto save(ItemDto itemDto, UserDetailsImpl userDetails) {
        itemDto.setPathToImage(ImageService.saveImage(itemDto.getImage()));

        ItemEntity itemEntity = itemMapper.itemDtoToItemEntity(itemDto);
        itemEntity.setUserUuid(userDetails.getUserUuid());
        ItemEntity savedEntity = itemRepository.save(itemEntity);

        return itemMapper.itemEntityToItemDto(savedEntity);
    }

    @Override
    public List<ItemDto> getAllUsersItems(UserDetailsImpl userDetails) {
        List<ItemEntity> itemEntityList = itemRepository.findByUserUuid(userDetails.getUserUuid());
        List<ItemDto> itemDtoList = itemEntityList.stream().map(x -> {
            ItemDto itemDto = itemMapper.itemEntityToItemDto(x);
            File file = new File(itemDto.getPathToImage());
            itemDto.setPathToImage("/" + file.getName());
            return itemDto;
        }).collect(Collectors.toList());
        return itemDtoList;
    }

}
