package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IdParam {

    @NotBlank
    private Integer id;
}
