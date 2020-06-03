package com.leverx.blog.service;

public interface MailService {

    void sendAuthCode(String to, String subject, String text);
}
