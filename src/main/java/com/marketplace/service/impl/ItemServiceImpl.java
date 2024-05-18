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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    private UUID itemUpdatedUuid;

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
        List<ItemEntity> itemEntityList = itemRepository.findAllByUserUuid(userDetails.getUserUuid());
        List<ItemDto> itemDtoList = itemEntityList.stream()
                .map(this::convertItemEntityToItemDto)
                .collect(Collectors.toList());
        return itemDtoList;
    }

    @Override
    public void deleteItem(List<UUID> selectedItems) {
        for (UUID uuid : selectedItems){
            itemRepository.deleteItemFromSelectByUuid(uuid, LocalDateTime.now());
        }
    }

    @Override
    public void setUpdatedItemUuid(UUID oldItemDtoUuid) {
        this.itemUpdatedUuid = oldItemDtoUuid;
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        itemRepository.findById(itemUpdatedUuid).map(updatedItemEntity -> {
            updatedItemEntity.setName(itemDto.getName());
            updatedItemEntity.setDescription(itemDto.getDescription());
            updatedItemEntity.setPrice(itemDto.getPrice());
            updatedItemEntity.setPathToImage(ImageService.saveImage(itemDto.getImage()));
            return itemRepository.save(updatedItemEntity);
        }).orElseThrow(() -> new RuntimeException("Doesn't exist"));

    }

    @Override
    public List<ItemDto> getAllItemsByRequest(String searchRequest) {
        List<ItemDto> itemDtoList = itemRepository.findByName("%" + searchRequest + "%").stream()
                .map(this::convertItemEntityToItemDto)
                .collect(Collectors.toList());
        return itemDtoList;
    }

    private ItemDto convertItemEntityToItemDto(ItemEntity itemEntity){
        ItemDto itemDto = itemMapper.itemEntityToItemDto(itemEntity);
        File file = new File(itemDto.getPathToImage());
        itemDto.setPathToImage("/" + file.getName());
        return itemDto;
    }
}
