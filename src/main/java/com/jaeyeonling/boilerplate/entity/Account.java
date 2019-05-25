package com.jaeyeonling.boilerplate.entity;

import com.jaeyeonling.boilerplate.type.UserRole;
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

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String profileImage;

    @Getter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;
}