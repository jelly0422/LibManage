package com.library.manage.model.param.emailParam;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailParam {
    @Email
    @NotBlank
    private String email;
}
