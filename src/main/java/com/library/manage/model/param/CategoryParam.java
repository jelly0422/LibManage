package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryParam {

    @NotBlank(message = "id不能为空")
    private String code;

    @NotBlank(message = "分类描述不能为空")
    private String description;

    private String parentid;
}
