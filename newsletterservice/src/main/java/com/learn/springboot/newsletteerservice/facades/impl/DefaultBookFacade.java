package com.learn.springboot.newsletteerservice.facades.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.facades.BookFacade;
import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.services.BookService;
import com.learn.springboot.newsletteerservice.services.CategoryService;

/**
 * Manages Books
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultBookFacade")
public class DefaultBookFacade implements BookFacade {

    @Autowired
    @Qualifier("defaultBookService")
    private BookService bookService;
    @Autowired
    @Qualifier("defaultCategoryService")
    private CategoryService categoryService;


    /**
     * Saves the Book {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    public BookModel save(BookModel model) {
        return getBookService().save(model);
    }


    /**
     * Gets the {@link BookModel} that has the {@code title} title
     * 
     * @param title
     *            the value to search on the database
     * @return a {@link BookModel} that thas the same {@code title} title
     */
    @Override
    public BookModel findOneByTitle(final String title) {
        return getBookService().findOneByTitle(title);
    }


    /**
     * Converts the {@code dto} into a {@link BookModel}
     * 
     * @param bookDto
     *            the object to be converted into a {@link BookModel}
     * @return the {@code dto} converted into a {@link BookModel}
     */
    @Override
    public BookModel convertDTO(final BookDTO bookDto) {
        BookModel model = findOneByTitle(bookDto.getTitle());
        if (model == null) {
            model = new BookModel();
        }
        model.setTitle(bookDto.getTitle());
        model.setSuperCategories(getSuperCategories(bookDto));
        return model;
    }


    /**
     * Gets all the parents Categories from the Book {@code book}
     * 
     * @param book
     *            the {@link BookModel} to have its parent Categories retrived
     * @return a list containing the {@code book}s parent Categories.
     */
    protected List<CategoryModel> getSuperCategories(BookDTO book) {
        final List<CategoryModel> superCategories = new ArrayList<CategoryModel>();
        for (CategoryDTO category : book.getCategoryCodes()) {
            final CategoryModel superCategory = getCategoryService()
                    .findOneByCode(category.getCode());
            if (superCategory != null) {
                superCategories.add(superCategory);
            }
        }
        return superCategories;
    }


    protected BookService getBookService() {
        return bookService;
    }


    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    protected CategoryService getCategoryService() {
        return categoryService;
    }


    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
