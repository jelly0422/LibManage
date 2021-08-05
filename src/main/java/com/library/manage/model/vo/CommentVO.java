package com.library.manage.model.vo;

import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.entity.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentVO {

    private CommentDTO comment;

    private Book book;
}
