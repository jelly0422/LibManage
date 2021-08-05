package com.library.manage.model.vo;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.entity.Book;
import lombok.Data;

@Data
public class AdminBookVO {

    private Book book;

    private CategoryDTO categoryDTO;
}
