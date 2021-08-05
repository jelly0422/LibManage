package com.library.manage.model.vo;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BookVO {

    private Book book;

    private CategoryDTO category;

    private List<CommentDTO> comment;

    private Boolean isFavor;

    private Boolean borrowStatus;
}
