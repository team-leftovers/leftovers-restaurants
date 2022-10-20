package com.leftovers.restaurants.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Builder
@AllArgsConstructor
public class JWTUser {
    private Long id;
    private String email;
    private String role;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + role);
    }
}
