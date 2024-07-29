package org.management.connectsphere.entites;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userTable")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer userId;

    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
    private String profilePic;
    private boolean enabled = false;
    private boolean emailVerified = false;

    private boolean phoneVerified = false;
    @Enumerated(EnumType.STRING)
    private Providers providers = Providers.SELF;
    private String providerId;

    //When user deletes his contact then all the contacts also gets deleted.
    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Contact> contactList ;
    @OneToMany(mappedBy="userLink",cascade = CascadeType.ALL)
    private List<SocialLink> socialLinks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }
}
