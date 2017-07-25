package com.learn.springboot.newsletteerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.learn.springboot.newsletteerservice.models.CategoryModel;

/**
 * Retrieves data from the database regarding Categories
 * 
 * @author felipe
 *
 */
public interface CategoryRepository
        extends JpaRepository<CategoryModel, Long>, QueryByExampleExecutor<CategoryModel> {
}
