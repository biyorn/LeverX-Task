package com.leverx.blog.security.token.jwt;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;
    private String password;
    private String refreshToken;

}
