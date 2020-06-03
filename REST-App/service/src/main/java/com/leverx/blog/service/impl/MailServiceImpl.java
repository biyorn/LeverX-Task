package com.leverx.blog.service.impl;

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
    public void sendAuthCode(String to, String subject, String text) {
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
