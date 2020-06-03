package com.leverx.blog.service;

import com.leverx.blog.dto.UserEntityDTO;

public interface UserService {

    UserEntityDTO signUp(UserEntityDTO userEntityDTO);

    void confirmUserEmail(String code);

}
