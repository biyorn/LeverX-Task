package com.leverx.blog.repository.redis;

import com.leverx.blog.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedisRepository extends JpaRepository<UserAuth, String> {
}
