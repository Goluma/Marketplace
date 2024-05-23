package com.marketplace.mapper;

import com.marketplace.domain.dto.ItemDto;
import com.marketplace.domain.dto.JournalDto;
import com.marketplace.domain.entity.ItemEntity;
import com.marketplace.domain.entity.JournalEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JournalMapper {

    public ModelMapper modelMapper;

    public JournalMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public JournalEntity journalDtoToJournalEntity(JournalDto journalDto){
        return modelMapper.map(journalDto, JournalEntity.class);
    }

    public JournalDto journalEntityToJournalDto(JournalEntity journalEntity){
        return modelMapper.map(journalEntity, JournalDto.class);
    }
}
