package com.learn.springboot.newsletteerservice.endpoints.dtos;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;

/**
 * The Book DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "title")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private String title;
    private Set<CategoryDTO> categoryCodes;


    /**
     * 
     */
    public BookDTO() {
        //
    }


    public BookDTO(final BookModel bookModel) {
        this.title = bookModel.getTitle();
        this.categoryCodes = new HashSet<>();
        for (CategoryModel c : bookModel.getSuperCategories()) {
            this.categoryCodes.add(new CategoryDTO(c));
        }
    }


    /**
     * 
     * @param props
     */
    @SuppressWarnings("unchecked")
    @JsonCreator
    public BookDTO(Map<String, Object> props) {
        this.title = String.valueOf(props.get("title"));
        final Set<CategoryDTO> superCategories = new HashSet<CategoryDTO>();
        for (String categoryCode : ((List<String>) props.get("categoryCodes"))) {
            final CategoryDTO superCategory = new CategoryDTO();
            superCategory.setCode(categoryCode);
            superCategories.add(superCategory);
        }
        this.categoryCodes = new HashSet<CategoryDTO>();
        this.categoryCodes.addAll(superCategories);
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Set<CategoryDTO> getCategoryCodes() {
        return categoryCodes;
    }


    public void setCategoryCodes(Set<CategoryDTO> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }
}
