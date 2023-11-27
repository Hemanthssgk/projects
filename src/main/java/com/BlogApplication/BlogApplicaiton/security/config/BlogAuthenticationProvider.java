package com.BlogApplication.BlogApplicaiton.security.config;

import com.BlogApplication.BlogApplicaiton.rest.repositories.UserRepository;
import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class BlogAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<User> userFromDb = userRepository.findByEmail(username);
        if (!userFromDb.isEmpty()) {
            if(passwordEncoder.matches(password,userFromDb.get(0).getPassword()))
            {
                // this will set the authenticated flag.
                return new UsernamePasswordAuthenticationToken(username,password,this.loadAuthorities(userFromDb));
            }
        }
        throw new BadCredentialsException("Either User Name Or Password is Incorrect");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private Collection<? extends GrantedAuthority> loadAuthorities(List<User> users) {
        return users.get(0).getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
