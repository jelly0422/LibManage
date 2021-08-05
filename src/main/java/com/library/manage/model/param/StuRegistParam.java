package com.library.manage.model.param;

import com.library.manage.model.enums.Sex;
import com.library.manage.model.enums.UserStatus;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class StuRegistParam {

    @NotBlank(message = "学号不能为空")
    @Size(min = 12 ,max = 12, message = "学号长度为12位")
    private String stuno;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 30)
    private String name;

    @NotBlank(message = "性别不能为空")
    private Sex sex;

    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度位6-20位")
    private String password;

    private final UserStatus status = UserStatus.NORMAL;
}
