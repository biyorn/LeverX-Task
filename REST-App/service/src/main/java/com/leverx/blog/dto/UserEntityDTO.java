package com.leverx.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntityDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private LocalDateTime createdAt;

}
