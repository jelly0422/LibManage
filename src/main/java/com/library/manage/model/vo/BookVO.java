package com.library.manage.model.vo;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class BookVO implements Serializable {

    private Book book;

    private CategoryDTO category;

    private List<CommentDTO> comment;

    private Boolean isFavor;

    private Boolean borrowStatus;
}
