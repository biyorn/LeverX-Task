package com.leverx.blog.service.user;

import com.leverx.blog.model.ResetPasswordEntity;
import com.leverx.blog.dto.UserEntityDTO;

public interface UserService {

    UserEntityDTO signUp(UserEntityDTO userEntityDTO);

    void confirmUserEmail(String code);

    void forgotPassword(ResetPasswordEntity resetPasswordEntity);

    void resetPassword(ResetPasswordEntity resetPasswordEntity);

}
