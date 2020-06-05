package com.leverx.blog.service.impl;

import com.leverx.blog.entity.UserAuth;
import com.leverx.blog.repository.redis.RedisRepository;
import com.leverx.blog.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Does it need to do another service for redis?
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RedisServiceImpl implements RedisService {

    private final RedisRepository redisRepository;

    // rename this method
    @Override
    public void addUserAuth(UserAuth userAuth) {
        throw new RuntimeException("Everything is good, but now you have problems");
    }

    @Override
    public String checkCode(String code) {
        return redisRepository.findById(code)
                .map(UserAuth::getId).toString();
    }

}
