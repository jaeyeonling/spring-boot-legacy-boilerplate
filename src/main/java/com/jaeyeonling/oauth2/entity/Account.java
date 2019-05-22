package com.jaeyeonling.oauth2.entity;

import com.jaeyeonling.oauth2.type.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Getter
@Setter
@Table
@Entity
@DynamicInsert
@DynamicUpdate
public class Account extends DateAuditPrimaryEntity {
    @Column(nullable = false)
    private String username;

    @Getter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;
}