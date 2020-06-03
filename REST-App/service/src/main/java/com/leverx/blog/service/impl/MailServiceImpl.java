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
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final SimpleMailMessage message;

    @Override
    public void sendAuthCode(UserAuth userAuth, String subject) {
        message.setTo(userAuth.getEmail());
        message.setSubject(subject);
        message.setText(userAuth.getId());
        mailSender.send(message);
    }
}
