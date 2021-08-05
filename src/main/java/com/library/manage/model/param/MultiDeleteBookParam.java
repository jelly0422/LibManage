package com.library.manage.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MultiDeleteBookParam {

    @NotBlank
    @JsonProperty("isbns")
    private String isbns;
}
