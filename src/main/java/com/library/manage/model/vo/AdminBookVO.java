package com.library.manage.model.vo;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.entity.Book;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminBookVO implements Serializable {

    private Book book;

    private CategoryDTO categoryDTO;
}
