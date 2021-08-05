package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {

    @NotBlank(message = "学号不能为空")
    private String stuno;

    @NotBlank(message = "密码不能为空")
    private String password;
}
