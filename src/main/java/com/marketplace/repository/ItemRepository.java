package com.marketplace.repository;

import com.marketplace.domain.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
}
