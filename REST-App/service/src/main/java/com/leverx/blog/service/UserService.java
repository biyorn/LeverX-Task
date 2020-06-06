package com.leverx.blog.service;

import com.leverx.blog.dto.ResetPasswordEntity;
import com.leverx.blog.dto.UserEntityDTO;

public interface UserService {

    UserEntityDTO signUp(UserEntityDTO userEntityDTO);

    void confirmUserEmail(String code);

    void forgotPassword(ResetPasswordEntity resetPasswordEntity);

    void resetPassword(ResetPasswordEntity resetPasswordEntity);

}
