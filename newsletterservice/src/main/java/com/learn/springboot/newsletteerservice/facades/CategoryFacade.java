package com.learn.springboot.newsletteerservice.facades;

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
public interface CategoryFacade {

    /**
     * Saves the Category {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    CategoryModel save(final CategoryModel model);


    /**
     * Gets the Books that {@code category} contains.
     * 
     * @param category
     *            the {@link CategoryModel} to retrieve its Books
     * @return a List containing all the Books from {@code category}
     */
    Collection<BookModel> getBooks(final CategoryModel category);


    /**
     * Returns the {@link CategoryModel} object that has the {@code code}
     * 
     * @param code
     *            the code to have its {@link CategoryModel} retrieved
     * @return a {@link CategoryModel}
     */
    CategoryModel findOne(final String code);


    /**
     * Converts the {@code category} into a {@link CategoryModel}
     * 
     * @param category
     *            the object to be converted into a {@link CategoryModel}
     * @return the {@code dto} converted into a {@link CategoryModel}
     */
    CategoryModel convertDTO(final CategoryDTO category);
}
