package com.library.manage.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class CategoryDTO {

    private Integer id;

    private String code;

    private String description;
}
