package com.marketplace.service.impl;

import com.marketplace.domain.dto.JournalDto;
import com.marketplace.mapper.JournalMapper;
import com.marketplace.repository.JournalRepository;
import com.marketplace.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private JournalMapper journalMapper;

    @Override
    public List<JournalDto> getCountStatistic(Integer count) {
        return journalRepository.getStatsByCount(count)
                .stream()
                .map(journalMapper::journalEntityToJournalDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JournalDto> getAllRecordsByUserUuid(UUID userUuid) {
        return journalRepository.getAllRecordsByUserUuid(userUuid)
                .stream().map(journalMapper::journalEntityToJournalDto)
                .collect(Collectors.toList());
    }
}
