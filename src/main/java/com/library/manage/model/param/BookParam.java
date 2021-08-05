package com.library.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookParam {

    @NotBlank
    @JsonProperty("isbn")
    private String ISBN;

    @NotBlank
    private String name;

    @NotBlank
    private String writer;

    @NotBlank
    private String introduction;

    @NotBlank
    private String publisher;

    @NotBlank
    private String category;

    @NotBlank
    private String position;

    @NotBlank
    private Integer stock;
}
