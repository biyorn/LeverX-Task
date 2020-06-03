package com.leverx.blog.repository;

import com.leverx.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(countQuery = "SELECT * FROM users u WHERE u.email = ?1", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

}
