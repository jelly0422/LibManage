package com.library.manage.model.dto;

import com.library.manage.model.enums.IsInTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BorrowInfoDTO {

    private String borrowstu;

    private Integer id;

    private String ISBN;

    private String category;

    private String borrowtime;

    private String rettime;

    private Integer readtime;

    private IsInTime isintime;

    private String name;

    private Boolean renew;
}
