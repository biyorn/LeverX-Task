package com.leverx.blog.service.impl;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.repository.redis.RedisRepository;
import com.leverx.blog.service.MailService;
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
    private final RedisRepository redisRepository;
    private final MailService mailService;

    @Override
    public UserEntityDTO signUp(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = userModelMapper.toEntity(userEntityDTO);
        addCreatedAt(userEntity);
        mailService.sendAuthCode(userEntity.getEmail());
        return userModelMapper.toDto(userRepository.save(userEntity));
    }


    private UserEntity addCreatedAt(UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        return userEntity;
    }
}
