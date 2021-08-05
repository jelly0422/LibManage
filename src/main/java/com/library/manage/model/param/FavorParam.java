package com.library.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class FavorParam {

    @NotBlank
    @Size(min = 13, max = 13)
    @JsonProperty(value = "isbn")
    private String ISBN;

    @NotBlank
    private String belongstu;
}
