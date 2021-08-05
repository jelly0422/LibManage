package com.library.manage.service;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.param.CategoryParam;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    CategoryDTO addCategory(CategoryParam categoryParam);

    CategoryDTO getCategory(String id);

}
