package com.leverx.blog.dto;

import lombok.Data;

// Maybe this entity does not need
@Data
public class ResetPasswordEntity {

    private String email;
    private String code;
    private String password;
}
