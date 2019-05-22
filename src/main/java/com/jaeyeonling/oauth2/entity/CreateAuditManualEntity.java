package com.jaeyeonling.oauth2.entity;

import com.jaeyeonling.oauth2.entity.converter.LocalDateTimeConverter;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class CreateAuditManualEntity extends ManualPrimaryEntity {
    @Getter
    @Column(nullable = false)
    @Convert(converter = LocalDateTimeConverter.class)
    @CreatedDate
    private LocalDateTime createdAt;

    //
    //
    //

    CreateAuditManualEntity(final long id) {
        super(id);
    }
}
