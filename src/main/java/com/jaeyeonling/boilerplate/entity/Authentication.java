package com.jaeyeonling.boilerplate.entity;

import com.jaeyeonling.boilerplate.model.SignUpRequest;
import com.jaeyeonling.boilerplate.security.PasswordEncoder;
import com.jaeyeonling.boilerplate.security.social.SocialUserInfo;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.type.UserRole;
import com.jaeyeonling.boilerplate.utils.BeanUtils;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "authProvider",
                        "userId",
                })
        }
)
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

    public static Authentication of(final SignUpRequest signUpRequest) {
        final var account = new Account();
        account.setUsername(signUpRequest.getUsername());
        account.setEmail(signUpRequest.getEmail());
        account.setRole(UserRole.USER);

        final var authentication = new Authentication();
        authentication.account = account;
        authentication.authProvider = AuthProvider.SERVER;
        authentication.userId = signUpRequest.getUserId();
        authentication.setPassword(signUpRequest.getPassword());

        return authentication;
    }

    public static Authentication of(final SocialUserInfo socialUserInfo) {
        final var account = new Account();
        account.setUsername(socialUserInfo.getNickname());
        account.setEmail(socialUserInfo.getEmail());
        account.setRole(UserRole.USER);

        final var authentication = new Authentication();
        authentication.account = account;
        authentication.authProvider = socialUserInfo.getAuthProvider();
        authentication.userId = socialUserInfo.getUserId();
        authentication.setPassword(socialUserInfo.getUserId()); // Note: password not null

        return authentication;
    }

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

    //
    //
    //

    private void setPassword(final String password) {
        this.password = BeanUtils.getBean(PasswordEncoder.class).encode(password);
    }
}
