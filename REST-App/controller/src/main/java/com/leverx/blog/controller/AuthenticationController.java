package com.leverx.blog.controller;

import com.leverx.blog.exception.UserException;
import com.leverx.blog.model.ResetPasswordEntity;
import com.leverx.blog.security.token.jwt.JwtRequest;
import com.leverx.blog.security.token.jwt.JwtResponse;
import com.leverx.blog.security.token.util.TokenUtil;
import com.leverx.blog.service.redis.RedisService;
import com.leverx.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final RedisService redisService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PatchMapping("/confirm/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmEmail(@PathVariable String code) {
        userService.confirmUserEmail(code);
    }

    @PostMapping
    public ResponseEntity<JwtResponse> createAuthenticationToken(@Validated @RequestBody JwtRequest jwtRequest) {
        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(accessToken));
    }

    @PostMapping(value = "/forgot_password")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestBody ResetPasswordEntity resetPasswordEntity) {
        userService.forgotPassword(resetPasswordEntity);
    }

    @PostMapping("/reset")
    public void resetPassword(@RequestBody ResetPasswordEntity resetPasswordEntity) {
        resetPasswordEntity.setPassword(passwordEncoder.encode(resetPasswordEntity.getPassword()));
        userService.resetPassword(resetPasswordEntity);
    }

    @GetMapping("/check_code/{code}")
    public ResponseEntity<String> checkCode(@PathVariable String code) {
        return ResponseEntity.ok(redisService.checkCode(code));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new UserException("Incorrect login or password", e);
        }
    }
}
