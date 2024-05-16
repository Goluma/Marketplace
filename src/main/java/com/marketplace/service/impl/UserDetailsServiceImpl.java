package com.marketplace.service.impl;

import com.marketplace.domain.UserDetailsImpl;
import com.marketplace.domain.entity.UserEntity;
import com.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmailLike(email);
        return userEntity.map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("There is no such user with email: " + email));
    }
}
