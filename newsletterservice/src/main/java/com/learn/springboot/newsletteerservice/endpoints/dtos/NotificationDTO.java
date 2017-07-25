package com.learn.springboot.newsletteerservice.endpoints.dtos;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The Notification DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "book")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationDTO {

    private BookDTO book;
    private Collection<CategoryDTO> categoryPaths;


    public BookDTO getBook() {
        return book;
    }


    public void setBook(BookDTO book) {
        this.book = book;
    }


    public Collection<CategoryDTO> getCategoryPaths() {
        return categoryPaths;
    }


    public void setCategoryPaths(Collection<CategoryDTO> categoryPaths) {
        this.categoryPaths = categoryPaths;
    }
}
