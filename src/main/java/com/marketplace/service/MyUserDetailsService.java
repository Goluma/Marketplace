package com.marketplace.service;

import com.marketplace.domain.MyUserDetails;
import com.marketplace.domain.entity.UserEntity;
import com.marketplace.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info(email);
        Optional<UserEntity> userEntity = userRepository.findByEmailLike(email);
        log.info("I was found" + userEntity.toString());
        return userEntity.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("There is no such user with email: " + email));
    }
}
