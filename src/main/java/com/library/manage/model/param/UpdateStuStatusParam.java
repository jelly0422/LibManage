package com.library.manage.model.param;

import com.library.manage.model.enums.UserStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateStuStatusParam {

    @NotBlank
    private String stuno;

    @NotBlank
    private UserStatus status;
}
