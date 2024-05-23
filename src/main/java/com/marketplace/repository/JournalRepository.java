package com.marketplace.repository;

import com.marketplace.domain.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JournalRepository extends JpaRepository<JournalEntity, UUID> {

    @Query(nativeQuery = true, value = "select * from journal limit :count")
    List<JournalEntity> getStatsByCount(@Param("count") Integer count);

    @Query(nativeQuery = true, value = "select * from journal where user_uuid = :user_uuid")
    List<JournalEntity> getAllRecordsByUserUuid(@Param("user_uuid") UUID user_uuid);
}
