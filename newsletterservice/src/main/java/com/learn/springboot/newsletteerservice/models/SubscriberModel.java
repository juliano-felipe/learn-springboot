package com.learn.springboot.newsletteerservice.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * 
 * @author felipe
 *
 */
@Entity(name = "subscribers")
public class SubscriberModel extends BaseModel {

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "subscriber_categories", joinColumns = {
            @JoinColumn(name = "subscriberPk", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "categoryPk", nullable = false, updatable = false) })
    private Set<CategoryModel> categories;


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Set<CategoryModel> getCategories() {
        return categories;
    }


    public void setCategories(Set<CategoryModel> categories) {
        this.categories = categories;
    }
}
