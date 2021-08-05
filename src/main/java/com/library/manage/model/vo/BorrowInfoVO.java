package com.library.manage.model.vo;

import com.library.manage.model.entity.BorrowInfo;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BorrowInfoVO {

    private BookVO Book;

    private BorrowInfo borrowInfo;
}
