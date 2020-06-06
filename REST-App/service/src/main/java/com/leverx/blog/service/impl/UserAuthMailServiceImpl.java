package com.leverx.blog.service.impl;

import com.leverx.blog.entity.UserAuth;
import com.leverx.blog.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserAuthMailServiceImpl implements MailService<UserAuth> {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage message;

    @Override
    public void sendMessage(UserAuth userAuth) {
        message.setTo(userAuth.getEmail());
        message.setSubject(userAuth.getSubject());
        message.setText(userAuth.getId());
        mailSender.send(message);
    }
}
