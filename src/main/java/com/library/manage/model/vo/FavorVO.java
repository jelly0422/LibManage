package com.library.manage.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class FavorVO {

    private String stu;

    private List<BookVO> books;
}
