package com.learn.springboot.newsletteerservice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.repositories.BookRepository;
import com.learn.springboot.newsletteerservice.repositories.CategoryRepository;
import com.learn.springboot.newsletteerservice.services.BookService;

/**
 * Manages Books
 * 
 * @author felipe
 *
 */
@Service
@Qualifier("defaultBookService")
public class DefaultBookService implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Persists the {@code model} on the database
     */
    @Override
    public BookModel save(final BookModel model) {
        return getBookRepository().save(model);
    }


    /**
     * Gets the {@link BookModel} that has the same {@code title} title
     * 
     * @param title
     *            the value to search on the database;
     * @return the {@link BookModel} that has the same {@code title} title
     */
    @Override
    public BookModel findOneByTitle(String title) {
        final BookModel bookExample = new BookModel();
        bookExample.setTitle(title);
        final Example<BookModel> example = Example.of(bookExample);
        return getBookRepository().findOne(example);
    }


    protected BookRepository getBookRepository() {
        return bookRepository;
    }


    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    protected CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }


    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
