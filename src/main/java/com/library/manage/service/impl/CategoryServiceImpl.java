package com.library.manage.service.impl;

import com.library.manage.dao.CategoryDao;
import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.param.CategoryParam;
import com.library.manage.service.CategoryService;
import com.library.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public CategoryDTO addCategory(CategoryParam categoryParam) {
        return null;
    }

    @Override
    public CategoryDTO getCategory(String id) {
        return BeanUtil.entityToDTO(CategoryDTO.class, Objects.requireNonNull(categoryDao.findById(id).orElse(null)));
    }
}
