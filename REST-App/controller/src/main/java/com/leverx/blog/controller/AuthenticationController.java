package com.leverx.blog.controller;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.exception.UserException;
import com.leverx.blog.security.token.jwt.JwtRequest;
import com.leverx.blog.security.token.jwt.JwtResponse;
import com.leverx.blog.security.token.util.TokenUtil;
import com.leverx.blog.service.UserService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    // maybe, need to create interface for token util
    private final TokenUtil tokenUtil;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PatchMapping("/auth/confirm/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmEmail(@PathVariable String code) {
        userService.confirmUserEmail(code);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserEntityDTO> signUp(@RequestBody UserEntityDTO userEntityDTO) {
        userEntityDTO.setPassword(passwordEncoder.encode(userEntityDTO.getPassword()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(userEntityDTO));
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        String refreshToken = tokenUtil.generateRefreshToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
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
