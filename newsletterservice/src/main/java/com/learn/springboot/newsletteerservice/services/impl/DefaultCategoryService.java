package com.learn.springboot.newsletteerservice.services.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.repositories.CategoryRepository;
import com.learn.springboot.newsletteerservice.services.CategoryService;

/**
 * Manages Categories
 * 
 * @author felipe
 *
 */
@Service
@Qualifier("defaultCategoryService")
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Persists the {@code model} on the database
     */
    public CategoryModel save(CategoryModel model) {
        return getCategoryRepository().save(model);
    }


    /**
     * Retrieves the {@link CategoryModel} that belongs to the {@code code}
     * 
     * @param code
     *            the code to have its {@link CategoryModel} retrieved
     * @return the {@link CategoryModel} that has the {@code code}
     */
    public CategoryModel findOneByCode(final String code) {
        final CategoryModel categoryExample = new CategoryModel();
        categoryExample.setCode(code);
        final Example<CategoryModel> example = Example.of(categoryExample);
        return getCategoryRepository().findOne(example);
    }


    /**
     * Gets the Books that belongs to the {@code category}
     */
    public Collection<BookModel> getBooks(CategoryModel category) {
        return category.getBooks();
    }


    protected CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }


    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
