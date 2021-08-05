package com.library.manage.model.param;

import com.library.manage.model.enums.Sex;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentParam {

    @NotBlank
    private String stuno;

    private String name;

    private Sex sex;

    private String email;

}
