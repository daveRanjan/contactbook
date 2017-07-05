package com.centralway.contactbook.security.auth.jwt;

import com.centralway.contactbook.model.User;
import com.centralway.contactbook.security.auth.JwtAuthenticationToken;
import com.centralway.contactbook.security.config.JwtSettings;
import com.centralway.contactbook.security.model.UserContext;
import com.centralway.contactbook.security.model.token.RawAccessJwtToken;
import com.centralway.contactbook.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtSettings jwtSettings;

    @Autowired UserService userService;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims
            .getBody()
            .getSubject();
        List<String> scopes = jwsClaims
            .getBody()
            .get("scopes", List.class);
        List<GrantedAuthority> authorities = scopes
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        Optional<User> authenticatedUser = userService.getByUserName(subject);
        UserContext context;
        if (authenticatedUser.isPresent()) {
            context = UserContext.create(authenticatedUser.get(), authorities);
        } else {
            throw new IllegalStateException("No User found by provided username");
        }

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
