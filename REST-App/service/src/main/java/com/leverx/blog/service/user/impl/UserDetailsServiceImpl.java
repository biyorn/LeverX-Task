package com.leverx.blog.service.user.impl;

import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String ROLE_USER = "USER";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email [" + email + "] does not exist"));
        checkUserStatus(userEntity);
        return User.builder()
                .username(email)
                .password(userEntity.getPassword())
                .roles(ROLE_USER)
                .build();
    }

    private void checkUserStatus(UserEntity userEntity) {
        if (!userEntity.isActive()) {
            throw new RuntimeException("Email [" + userEntity.getEmail() + "] is not confirmed");
        }
    }
}
