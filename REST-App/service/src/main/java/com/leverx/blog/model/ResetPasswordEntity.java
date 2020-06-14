package com.leverx.blog.model;

import lombok.Data;

@Data
public class ResetPasswordEntity {

    private String email;
    private String code;
    private String password;
}
