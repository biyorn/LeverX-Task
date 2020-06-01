package com.leverx.blog.service.impl;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private final CommonModelMapper<UserEntity, UserEntityDTO> userModelMapper;
    private final UserRepository userRepository;

    @Override
    public UserEntityDTO signIn(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = userModelMapper.toEntity(userEntityDTO);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userModelMapper.toDto(userEntity);
    }
}
