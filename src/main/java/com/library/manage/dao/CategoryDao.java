package com.library.manage.dao;

import com.library.manage.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author jelly
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Optional<Category> findCategoryByCode(String code);
}
