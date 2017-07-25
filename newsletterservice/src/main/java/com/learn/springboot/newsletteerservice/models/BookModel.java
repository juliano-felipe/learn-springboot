package com.learn.springboot.newsletteerservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author felipe
 *
 */
@Entity(name = "books")
public class BookModel extends BaseModel {

    @NotNull
    @Column(name = "title", unique = false, nullable = false)
    private String title;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_categories", joinColumns = {
            @JoinColumn(name = "bookPk", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "categoryPk", nullable = false, updatable = false) })
    private List<CategoryModel> superCategories;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public List<CategoryModel> getSuperCategories() {
        return superCategories;
    }


    public void setSuperCategories(List<CategoryModel> superCategories) {
        this.superCategories = superCategories;
    }
}
