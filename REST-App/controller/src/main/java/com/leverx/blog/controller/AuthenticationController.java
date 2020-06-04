package com.leverx.blog.controller;

import com.leverx.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationController {

    private final UserService userService;

    @PatchMapping("/confirm/{code}")
    @ResponseStatus(HttpStatus.OK)
    public void confirmEmail(@PathVariable String code) {
        userService.confirmUserEmail(code);
    }
}
