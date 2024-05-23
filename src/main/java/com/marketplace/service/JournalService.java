package com.marketplace.service;

import com.marketplace.domain.dto.JournalDto;

import java.util.List;
import java.util.UUID;

public interface JournalService {
    List<JournalDto> getCountStatistic(Integer count);

    List<JournalDto> getAllRecordsByUserUuid(UUID userUuid);
}
