package com.library.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.manage.util.DateUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CommentParam {

    @NotBlank(message = "评论者不能为空")
    private String author;

    @NotBlank(message = "评论内容不能为空")
    @Size(min = 20, max = 200, message = "评论长度为{min}-{max}")
    private String content;

    @NotBlank(message = "书籍不能为空")
    @JsonProperty("isbn")
    private String ISBN;

    private Double score = 0d;

    private final Date releasetime = new Date();

    private String writer="123";
}
