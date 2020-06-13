package com.leverx.blog.entity.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public enum ArticleStatus {

    PUBLIC("public"), DRAFT("draft");

    private final String name;
}
