package com.marketplace.repository;

import com.marketplace.domain.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {


}
