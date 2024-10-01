package com.github.group37.roadmap.securityConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.group37.roadmap.percistance.models.StudentDao;
import com.github.group37.roadmap.percistance.StudentRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<StudentDao> currentUser = repository.findByEmail(email);

        if (currentUser.isPresent()) {
            StudentDao user = currentUser.get();

            System.out.println(user.getEmail());

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    email,
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    authorities
            );

            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not authorized.");
        }
    }
}



