package com.leverx.blog.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Builder
@Getter
@RedisHash("userAuth")
@RequiredArgsConstructor
public class UserAuth {

    @Id
    private String id;
    private String email;
    private String subject;
}
