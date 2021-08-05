package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminParam {

    @NotBlank(message = "账号不能为空")
    private String accnum;

    @NotBlank(message = "密码不能为空")
    private String password;
}
