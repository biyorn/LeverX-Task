package com.leverx.blog.service;

public interface MailService<T> {

    void sendMessage(T t);
}
