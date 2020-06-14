package com.leverx.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leverx.blog.dto.transfer.New;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private int id;
    @NotNull(message = "Comment must be filled", groups = New.class)
    private String message;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserEntityDTO userEntity;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
}
