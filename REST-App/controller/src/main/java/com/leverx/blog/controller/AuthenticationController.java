package com.leverx.blog.controller;

import com.leverx.blog.dto.UserEntityDTO;
import com.leverx.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationController {

    private final UserService userService;

    @PatchMapping("/auth/confirm/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmEmail(@PathVariable String code) {
        userService.confirmUserEmail(code);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserEntityDTO> signUp(@RequestBody UserEntityDTO userEntityDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.signUp(userEntityDTO));
    }
}
