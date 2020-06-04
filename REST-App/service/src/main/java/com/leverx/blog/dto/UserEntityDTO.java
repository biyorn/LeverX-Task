package com.leverx.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserEntityDTO {

    private int id;
    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 20, message = "First name cannot be less than 3 symbols")
    private String firstName;
    private String lastName;
    private String password;
    @Email
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private boolean isActive;

}
