package com.leverx.blog.controller;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.dto.transfer.New;
import com.leverx.blog.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<UserEntityDTO> signUp(@Validated(New.class) @RequestBody UserEntityDTO userEntityDTO) {
        userEntityDTO.setPassword(passwordEncoder.encode(userEntityDTO.getPassword()));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(userEntityDTO));
    }
}
