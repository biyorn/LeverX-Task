package com.leverx.blog.service;

import com.leverx.blog.entity.UserAuth;

public interface MailService {

    void sendAuthCode(UserAuth userAuth, String subject);
}
