package com.leftovers.restaurants.security;

import com.leftovers.restaurants.exception.NoSuchAccountException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class NullDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws NoSuchAccountException {
        return User.withUsername("")
                .build();
    }
}
