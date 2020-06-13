package com.leverx.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leverx.blog.entity.enumeration.ArticleStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {

    private int id;
    private UserEntityDTO userEntity;
    private String title;
    private String text;
    private ArticleStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
}
