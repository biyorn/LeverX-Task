package com.leverx.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leverx.blog.dto.transfer.New;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserEntityDTO {

    private int id;
    @Size(min = 3, max = 20, message = "First name cannot be less than 3 symbols", groups = New.class)
    private String firstName;
    @Size(min = 3, max = 20, message = "Last name must be filled", groups = New.class)
    private String lastName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 15, message = "Password must has from 6 to 15 symbols", groups = New.class)
    private String password;
    @NotNull(message = "Email must be filled", groups = New.class)
    @Email(message = "Email must be correct, has '@' and email address", groups = New.class)
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isActive;

}
