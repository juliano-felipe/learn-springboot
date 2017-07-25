package com.learn.springboot.newsletteerservice.services;

import java.util.Collection;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;

/**
 * Manages Categories
 * 
 * @author felipe
 *
 */
public interface CategoryService extends Service<CategoryModel, CategoryDTO> {

    /**
     * Retrives the Books that the {@code category} contains.
     * 
     * @param category
     *            the {@link CategoryModel} to have its Books retrieved
     * @return a Collection containing the Books that belongs to the
     *         {@code category}
     */
    Collection<BookModel> getBooks(final CategoryModel category);


    /**
     * Gets the {@link CategoryModel} that belongs to the {@code code}
     * 
     * @param code
     *            the code to have its {@link CategoryModel} retrieved
     * @return a {@link CategoryModel}
     */
    CategoryModel findOneByCode(final String code);
}
