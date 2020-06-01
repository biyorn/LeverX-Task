package com.leverx.blog.repository;

import com.leverx.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
//
//    UserEntity save(UserEntity userEntity);
}
