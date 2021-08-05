package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class BorrowParam {

    @NotBlank
    private String borrowstu;

    @NotBlank
    private  String ISBN;

    @NotBlank
    private String category;

    @NotBlank
    @Positive
    private Integer readtime;

    @NotBlank
    private String name;

    private final Date borrowtime = new Date();
}
