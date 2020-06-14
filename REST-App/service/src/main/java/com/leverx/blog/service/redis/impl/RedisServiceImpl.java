package com.leverx.blog.service.redis.impl;

import com.leverx.blog.entity.UserAuth;
import com.leverx.blog.repository.redis.RedisRepository;
import com.leverx.blog.service.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RedisServiceImpl implements RedisService {

    private final RedisRepository redisRepository;

    @Override
    public String checkCode(String code) {
        return redisRepository.findById(code)
                .map(UserAuth::getId).toString();
    }

}
