package com.library.manage.model.vo;

import com.library.manage.model.dto.CategoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {

    private String id;

    private String description;

    private List<CategoryDTO> childs;
}
