package com.learn.springboot.newsletteerservice.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.learn.springboot.newsletteerservice.services.CategoryService;

/**
 * Validates an Object
 * 
 * @author felipe
 *
 */
public abstract class AbstractValidator implements Validator {

    @Autowired
    @Qualifier("defaultCategoryService")
    private CategoryService categoryService;


    /**
     * Validates if the {@code value} is null or blank. If the {@code value} is null
     * or blank, it adds a message in the {@code errors} stating it.
     * 
     * @param field
     *            the field name
     * @param value
     *            the value to be validated
     */
    protected void validateNotNull(final String field, final Object value, final Errors errors) {
        if (value == null || StringUtils.isBlank(String.valueOf(value))) {
            errors.rejectValue(field, String.format("Field %s cannot be empty.", field));
        }
    }


    /**
     * Validates if a category exists
     * 
     * @param categoryCode
     *            the category code to check
     * @param errors
     */
    protected void validateCategoryExists(final String categoryCode, final Errors errors) {
        if (getCategoryService().findOneByCode(categoryCode) == null) {
            errors.rejectValue("categoryCodes", String.format("Category %s doesn't exists",
                    categoryCode));
        }
    }


    protected CategoryService getCategoryService() {
        return categoryService;
    }


    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
