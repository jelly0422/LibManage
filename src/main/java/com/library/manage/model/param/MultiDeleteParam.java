package com.library.manage.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MultiDeleteParam {
    @NotBlank
    private List<Integer> ids;
}
