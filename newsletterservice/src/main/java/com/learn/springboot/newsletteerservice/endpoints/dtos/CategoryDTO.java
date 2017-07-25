package com.learn.springboot.newsletteerservice.endpoints.dtos;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learn.springboot.newsletteerservice.models.CategoryModel;

/**
 * The Category DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "title")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private String code;
    private String title;
    private CategoryDTO superCategory;


    /**
     * 
     */
    public CategoryDTO() {
        //
    }


    public CategoryDTO(final CategoryModel categoryModel) {
        this.code = categoryModel.getCode();
        this.title = categoryModel.getTitle();
        if (categoryModel.getSuperCategory() != null) {
            this.superCategory = new CategoryDTO(categoryModel.getSuperCategory());
        }
    }


    /**
     * 
     * @param props
     */
    @JsonCreator
    public CategoryDTO(Map<String, Object> props) {
        this.code = String.valueOf(props.get("code"));
        this.title = String.valueOf(props.get("title"));
        final CategoryDTO superCategory = new CategoryDTO();
        superCategory.setCode(String.valueOf(props.get("superCategory")));
        this.superCategory = superCategory;
    }


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


    public CategoryDTO getSuperCategory() {
        return superCategory;
    }


    public void setSuperCategory(CategoryDTO superCategory) {
        this.superCategory = superCategory;
    }
}
