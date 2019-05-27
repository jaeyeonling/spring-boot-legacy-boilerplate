package com.jaeyeonling.boilerplate.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.type.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Setter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "authProvider",
                        "userId",
                })
        }
)
@JsonIgnoreProperties(value = {
        "authProvider",
        "password",
})
@Entity
@DynamicInsert
@DynamicUpdate
public class Authentication extends AutoPrimaryEntity {

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AuthProvider authProvider;

    @Getter
    @Column(nullable = false)
    private String userId;

    @Getter
    @Column(nullable = false)
    private String password;

    @Getter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Account account;

    //
    //
    //

    public UserRole getRole() {
        return account.getRole();
    }

    public long getOwner() {
        return account.getId();
    }

    public boolean isVerify(final String inputPassword) {
        return authProvider.verify(
                this,
                inputPassword
        );
    }
}
