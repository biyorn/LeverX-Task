package com.leverx.blog.service.redis;

import com.leverx.blog.entity.UserAuth;

public interface RedisService {

    void addUserAuth(UserAuth userAuth);

    String checkCode(String code);
}
