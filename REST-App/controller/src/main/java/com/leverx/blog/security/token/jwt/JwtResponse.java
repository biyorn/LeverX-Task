package com.leverx.blog.security.token.jwt;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class JwtResponse {

    private String accessToken;

}
