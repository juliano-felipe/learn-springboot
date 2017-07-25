package com.learn.springboot.newsletteerservice.services;

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.models.BookModel;

/**
 * Manages Books
 * 
 * @author felipe
 *
 */
public interface BookService extends Service<BookModel, BookDTO> {

    /**
     * Gets the {@link BookModel} that has the same {@code title} title
     * 
     * @param title
     *            the value to search on the database;
     * @return the {@link BookModel} that has the same {@code title} title
     */
    BookModel findOneByTitle(final String title);
}
