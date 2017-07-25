package com.learn.springboot.newsletteerservice.facades;

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.models.BookModel;

/**
 * Manages Books
 * 
 * @author felipe
 *
 */
public interface BookFacade {

    /**
     * Saves the Book {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    BookModel save(final BookModel model);


    /**
     * Gets the {@link BookModel} that has the {@code title} title
     * 
     * @param title
     *            the value to search on the database
     * @return a {@link BookModel} that thas the same {@code title} title
     */
    BookModel findOneByTitle(final String title);


    /**
     * Converts the {@code dto} into a {@link BookModel}
     * 
     * @param dto
     *            the object to be converted into a {@link BookModel}
     * @return the {@code dto} converted into a {@link BookModel}
     */
    BookModel convertDTO(final BookDTO dto);
}
