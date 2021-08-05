package com.library.manage.model.param.emailParam;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CodeParam {
    @NotBlank
    private String code;
}
