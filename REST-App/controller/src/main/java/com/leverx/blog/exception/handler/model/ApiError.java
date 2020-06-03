package com.leverx.blog.exception.handler.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ApiError {

    private String message;
}
