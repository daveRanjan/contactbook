package com.centralway.contactbook.security.model.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;
import lombok.Data;

@Data
public final class AccessJwtToken implements JwtToken {
    private final String token;
    @JsonIgnore private Claims claims;

    protected AccessJwtToken(final String token, Claims claims) {
        this.token = token;
        this.claims = claims;
    }

    @Override
    public String getToken() {
        return token;
    }
}
