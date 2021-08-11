package com.library.manage.model.vo;

import com.library.manage.model.entity.BorrowInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BorrowInfoVO implements Serializable {

    private BookVO Book;

    private BorrowInfo borrowInfo;
}
