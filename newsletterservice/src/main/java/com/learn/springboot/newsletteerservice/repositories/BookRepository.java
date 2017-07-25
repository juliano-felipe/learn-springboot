package com.learn.springboot.newsletteerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.learn.springboot.newsletteerservice.models.BookModel;

/**
 * Retrieves data from the database regarding Books
 * 
 * @author felipe
 *
 */
public interface BookRepository
        extends JpaRepository<BookModel, Long>, QueryByExampleExecutor<BookModel> {
}
