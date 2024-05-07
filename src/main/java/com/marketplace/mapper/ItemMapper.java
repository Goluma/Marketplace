package com.marketplace.mapper;

import com.marketplace.domain.dto.ItemDto;
import com.marketplace.domain.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class ItemMapper {

    public ModelMapper modelMapper;

    public ItemMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ItemEntity itemDtoToItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, ItemEntity.class);
    }

    public ItemDto itemEntityToItemDto(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDto.class);
    }
}
