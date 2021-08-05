package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateStuParam {

    @NotBlank
    private String stuno;

    @NotBlank
    private String key;

    private String val;
}
