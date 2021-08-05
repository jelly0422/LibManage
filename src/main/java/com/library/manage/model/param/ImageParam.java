package com.library.manage.model.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageParam {

    private MultipartFile multipartFile;

    private String isbn;
}
