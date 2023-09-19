package com.example.project.security;

import com.example.project.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {


    private final User user;
    private String username;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the user's email address, which is used as the username for authentication.
     *
     * @return The user's email address (username).
     */
    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    /**
     * Indicates whether the user's account is non-expired (true in this case).
     *
     * @return true if the user's account is non-expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is non-locked (true in this case).
     *
     * @return true if the user's account is non-locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are non-expired (true in this case).
     *
     * @return true if the user's credentials are non-expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is enabled (true in this case).
     *
     * @return true if the user's account is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the User entity associated with this UserDetails implementation.
     *
     * @return The User entity.
     */
    public User getUser() {
        return user;
    }
}
