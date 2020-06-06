package com.leverx.blog.service.impl;

import com.leverx.blog.dto.ResetPasswordEntity;
import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.entity.UserAuth;
import com.leverx.blog.entity.UserEntity;
import com.leverx.blog.exception.FailedAddObjectException;
import com.leverx.blog.exception.FailedUpdateObjectException;
import com.leverx.blog.exception.NotFoundObjectException;
import com.leverx.blog.mapper.CommonModelMapper;
import com.leverx.blog.repository.UserRepository;
import com.leverx.blog.repository.redis.RedisRepository;
import com.leverx.blog.service.MailService;
import com.leverx.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

    private static final String VERIFY_EMAIL = "Verify email address";
    private static final String PASSWORD_RESET = "Password reset";
    private static final boolean EMAIL_CONFIRMED = true;
    private static final int LENGTH_AUTH_CODE = 10;

    private final CommonModelMapper<UserEntity, UserEntityDTO> userModelMapper;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;
    private final MailService<UserAuth> mailService;

    @Override
    @Transactional
    public UserEntityDTO signUp(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = userModelMapper.toEntity(userEntityDTO);
        String email = userEntity.getEmail();
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new FailedAddObjectException("Email [" + email + "] already exists");
        });
        userEntity.setCreatedAt(getCurrentTime());
        UserAuth userAuth = UserAuth.builder()
                .id(createAuthCode())
                .email(email)
                .subject(VERIFY_EMAIL)
                .build();
        redisRepository.save(userAuth);
        mailService.sendMessage(userAuth);
        return userModelMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void confirmUserEmail(String code) {
        redisRepository.findById(code).ifPresentOrElse(userAuth -> {
            userRepository.findByEmail(userAuth.getEmail()).ifPresent(user -> {
                user.setActive(EMAIL_CONFIRMED);
                userRepository.save(user);
                redisRepository.delete(userAuth);
            });
        }, () -> {
            throw new FailedUpdateObjectException("This code is not exist");
        });
    }

    @Override
    @Transactional
    public void forgotPassword(ResetPasswordEntity resetPasswordEntity) {
        String email = resetPasswordEntity.getEmail();
        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            UserAuth userAuth = UserAuth.builder()
                    .id(createAuthCode())
                    .email(email)
                    .subject(PASSWORD_RESET)
                    .build();
            redisRepository.save(userAuth);
            mailService.sendMessage(userAuth);
        }, () -> {
            throw new NotFoundObjectException("Email [" + email + "] does not exist");
        });
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordEntity resetPasswordEntity) {
        UserAuth userAuth = redisRepository.findById(resetPasswordEntity.getCode())
                .orElseThrow(() -> new NotFoundObjectException("This code is invalid"));
        userRepository.findByEmail(userAuth.getEmail()).ifPresent(user -> {
            user.setPassword(resetPasswordEntity.getPassword());
            userRepository.save(user);
            redisRepository.delete(userAuth);
        });
    }

    private String createAuthCode() {
        return RandomStringUtils.randomAlphanumeric(LENGTH_AUTH_CODE);
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
