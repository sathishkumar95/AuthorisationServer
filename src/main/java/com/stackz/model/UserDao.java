package com.stackz.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserDao extends User {

    List<String> permissions;

    public UserDao(String username, String password, Collection<? extends GrantedAuthority> authorities, List<String> permissions) {
        super(username, password, authorities);
        this.permissions = permissions;
    }

    public UserDao(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
