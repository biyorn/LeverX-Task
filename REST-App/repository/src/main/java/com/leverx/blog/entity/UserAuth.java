package com.leverx.blog.entity;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Value
@RedisHash("userAuth")
@RequiredArgsConstructor
public class UserAuth {

    @Id
    private String id;
    private String code;
}
