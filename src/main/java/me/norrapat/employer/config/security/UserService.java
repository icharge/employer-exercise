package me.norrapat.employer.config.security;

import lombok.extern.log4j.Log4j2;
import me.norrapat.employer.entity.User;
import me.norrapat.employer.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Log4j2
@Service
public class UserService implements UserDetailsService {

    private final UserRepo repository;

    public UserService(UserRepo repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (log.isDebugEnabled()) {
            log.debug("Load user name {}", username);
        }

        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // User default role for now.
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.singletonList(authority)
        );
    }
}
