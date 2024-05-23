package com.marketplace.repository;

import com.marketplace.domain.entity.ItemEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {


    @Query(nativeQuery = true, value = "select * from item_entity where user_uuid = :user_uuid and deleted_at is null")
    List<ItemEntity> findAllByUserUuid(@Param("user_uuid") UUID user_uuid);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update item_entity set deleted_at = :deleted_at where item_uuid = :item_uuid")
    void deleteItemFromSelectByUuid(@Param("item_uuid") UUID item_uuid, @Param("deleted_at")LocalDateTime now);

    @Query(nativeQuery = true, value = "select * from item_entity where name like :name and deleted_at is null")
    List<ItemEntity> findByName(@Param("name")String name);


}
