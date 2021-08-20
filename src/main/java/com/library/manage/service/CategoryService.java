package com.library.manage.service;

import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.param.CategoryParam;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    /**
     * 添加分类
     * @param categoryParam 分类参数
     * @return
     */
    CategoryDTO addCategory(CategoryParam categoryParam);

    CategoryDTO getCategory(Integer id);

    CategoryDTO getCategory(String code);
}
