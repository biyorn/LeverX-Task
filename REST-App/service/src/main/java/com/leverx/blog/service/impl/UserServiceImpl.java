package com.leverx.blog.service.impl;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.entity.UserAuth;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.exception.FailedAddObjectException;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.repository.redis.RedisRepository;
import com.leverx.blog.service.MailService;
import com.leverx.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private static final boolean EMAIL_CONFIRMED = true;
    private final CommonModelMapper<UserEntity, UserEntityDTO> userModelMapper;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;
    private final MailService mailService;

    @Override
    public UserEntityDTO signUp(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = userModelMapper.toEntity(userEntityDTO);
        String email = userEntity.getEmail();
        userRepository.findByEmail(email)
                .ifPresentOrElse(user -> {
                    throw new FailedAddObjectException("Email [" + email + "] already exists");
                }, () -> {
                    addCreatedAt(userEntity);
                    UserAuth userAuth = new UserAuth(RandomStringUtils.randomAlphanumeric(10),
                            userEntity.getEmail());
                    mailService.sendAuthCode(userAuth, "Authorization");
                    redisRepository.save(userAuth);
                });
        return userModelMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public void confirmUserEmail(String code) {
        redisRepository.findById(code).ifPresentOrElse(userAuth -> {
            userRepository.findByEmail(userAuth.getEmail())
                    .ifPresent(user -> {
                        user.setActive(EMAIL_CONFIRMED);
                        userRepository.save(user);
                    });
        }, () -> {
            throw new FailedUpdateObjectException("This code is not exist");
        });
    }

    private UserEntity addCreatedAt(UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDateTime.now());
        return userEntity;
    }
}
