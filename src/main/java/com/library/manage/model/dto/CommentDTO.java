package com.library.manage.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class CommentDTO {

    private String author;

    private String content;

    private String releasetime;

    private String ISBN;

    private Integer id;

    private Double score;

    private String writer;
}
