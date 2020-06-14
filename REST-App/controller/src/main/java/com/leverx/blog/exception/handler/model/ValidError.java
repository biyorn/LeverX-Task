package com.leverx.blog.exception.handler.model;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Map;

@Value
@RequiredArgsConstructor
public class ValidError {

    private Map<String, String> errors;
}
