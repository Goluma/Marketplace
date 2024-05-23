package com.marketplace.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "journal")
public class JournalEntity {

    @Id
    @GeneratedValue
    private UUID recordUuid;

    private UUID userUuid;

    private String queryName;

    private String tableName;

    private LocalDateTime dateTime;

}
