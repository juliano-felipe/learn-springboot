package com.learn.springboot.newsletteerservice.facades.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.facades.CategoryFacade;
import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.services.CategoryService;

/**
 * Manages Categories
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultCategoryFacade")
public class DefaultCategoryFacade implements CategoryFacade {

    @Autowired
    @Qualifier("defaultCategoryService")
    private CategoryService categoryService;


    /**
     * Saves the Category {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    public CategoryModel save(CategoryModel model) {
        return getCategoryService().save(model);
    }


    /**
     * Gets the Books that {@code category} contains.
     * 
     * @param category
     *            the {@link CategoryModel} to retrieve its Books
     * @return a List containing all the Books from {@code category}
     */
    public Collection<BookModel> getBooks(CategoryModel category) {
        final Set<BookModel> books = new HashSet<>();
        if (CollectionUtils.isNotEmpty(category.getChildCategories())) {
            for (CategoryModel c : category.getChildCategories()) {
                books.addAll(getBooks(c));
            }
        }
        books.addAll(category.getBooks());
        return books;
    }


    /**
     * Returns the {@link CategoryModel} object that has the {@code code}
     * 
     * @param code
     *            the code to have its {@link CategoryModel} retrieved
     * @return a {@link CategoryModel}
     */
    public CategoryModel findOne(String code) {
        return getCategoryService().findOneByCode(code);
    }


    /**
     * Converts the {@code category} into a {@link CategoryModel}
     * 
     * @param category
     *            the object to be converted into a {@link CategoryModel}
     * @return the {@code dto} converted into a {@link CategoryModel}
     */
    public CategoryModel convertDTO(final CategoryDTO category) {
        CategoryModel superCategory = getCategoryService()
                .findOneByCode(category.getSuperCategory().getCode());
        CategoryModel model = findOne(category.getCode());
        if (model == null) {
            model = new CategoryModel();
        }
        model.setCode(category.getCode());
        model.setTitle(category.getTitle());
        model.setSuperCategory(superCategory);
        return model;
    }


    protected CategoryService getCategoryService() {
        return categoryService;
    }


    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
