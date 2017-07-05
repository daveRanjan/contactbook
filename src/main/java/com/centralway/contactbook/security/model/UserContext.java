package com.centralway.contactbook.security.model;

import com.centralway.contactbook.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserContext {
    private final Long id;
    private final String username;
    private final List<GrantedAuthority> authorities;

    private UserContext(Long id, String username, List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public static UserContext create(User user, List<GrantedAuthority> authorities) {
        if (StringUtils.isEmpty(user.getUserName())) throw new IllegalArgumentException("Username is blank: " + user.getUserName());
        return new UserContext(user.getId(), user.getUserName(), authorities);
    }

    public static UserContext create(User user) {
        if (StringUtils.isEmpty(user.getUserName())) throw new IllegalArgumentException("Username is blank: " + user.getUserName());
        List<GrantedAuthority> authorities = user
            .getRoles()
            .stream()
            .map(authority -> new SimpleGrantedAuthority(authority
                .getRole()
                .authority()))
            .collect(Collectors.toList());
        return new UserContext(user.getId(), user.getUserName(), authorities);
    }
}
