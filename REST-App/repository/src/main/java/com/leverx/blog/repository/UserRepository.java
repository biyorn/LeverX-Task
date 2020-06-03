package com.leverx.blog.repository;

import com.leverx.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    @Modifying
    @Query(countQuery = "UPDATE UserEntity u SET u.active = :active WHERE u.email = :email", nativeQuery = true)
    int setActive(boolean active, String email);
}
