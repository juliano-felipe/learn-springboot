package com.learn.springboot.newsletteerservice.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author felipe
 *
 */
@Entity(name = "categories")
public class CategoryModel extends BaseModel {

    @Column(name = "code", unique = true, nullable = false)
    private String code;
    @Column(name = "title", unique = false, nullable = false)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "superCategory")
    private CategoryModel superCategory;
    @OneToMany(mappedBy = "superCategory")
    private List<CategoryModel> childCategories;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "superCategories")
    private Set<BookModel> books;


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public CategoryModel getSuperCategory() {
        return superCategory;
    }


    public void setSuperCategory(CategoryModel superCategory) {
        this.superCategory = superCategory;
    }


    public List<CategoryModel> getChildCategories() {
        return childCategories;
    }


    public void setChildCategories(List<CategoryModel> childCategories) {
        this.childCategories = childCategories;
    }


    public Set<BookModel> getBooks() {
        return books;
    }


    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }
}
