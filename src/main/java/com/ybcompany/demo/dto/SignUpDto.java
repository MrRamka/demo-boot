package com.ybcompany.demo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {
    @Length(min = 4)
    private String username;

    @Length(min = 1)
    private String fullName;

    @Length(min = 8)
    private String password;

}
