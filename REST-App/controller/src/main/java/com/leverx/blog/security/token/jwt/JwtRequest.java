package com.leverx.blog.security.token.jwt;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class JwtRequest {

    @NotNull(message = "Email must be filled")
    private String email;
    @NotNull(message = "Password must be filled")
    private String password;

}
