package com.library.manage.model.param;

import com.library.manage.model.entity.Book;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UpdateBookParam {

    @NotBlank
    private List<Book> books;
}
