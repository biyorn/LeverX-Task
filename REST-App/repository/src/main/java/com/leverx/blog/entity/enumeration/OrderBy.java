package com.leverx.blog.entity.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderBy {

    ASC("asc", true), DESC("desc", false);

    private final String name;
    private final boolean isAsc;
}
