package com.leverx.blog.service.mail;

public interface MailService<T> {

    void sendMessage(T t);
}
