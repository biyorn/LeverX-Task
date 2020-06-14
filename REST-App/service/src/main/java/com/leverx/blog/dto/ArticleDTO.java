package com.leverx.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leverx.blog.dto.transfer.New;
import com.leverx.blog.dto.transfer.Update;
import com.leverx.blog.entity.Comment;
import com.leverx.blog.entity.enumeration.ArticleStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleDTO {

    private int id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserEntityDTO userEntity;
    @Size(min = 4, max = 20, message = "Title must has from 4 to 20 symbols",
            groups = {New.class, Update.class})
    private String title;
    @NotNull(message = "Text must be filled", groups = {New.class, Update.class})
    private String text;
    @NotNull(message = "Status must be PUBLIC or DRAFT", groups = {New.class, Update.class})
    private ArticleStatus status;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
    private List<Comment> comments;
}
