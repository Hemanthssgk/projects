package com.BlogApplication.BlogApplicaiton.config;

import com.BlogApplication.BlogApplicaiton.repositories.RoleRepo;
import com.BlogApplication.BlogApplicaiton.repositories.UserRepository;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDefinedUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(username);

        return new org.springframework.security.core.userdetails.User(users.get(0).getEmail(),users.get(0).getPassword(),LoadAuthorities(users));
    }

    private Collection<? extends GrantedAuthority> LoadAuthorities(List<User> users) {
        return users.get(0).getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
