package com.learn.springboot.newsletteerservice.validators.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.validators.AbstractValidator;

/**
 * Validates a {@link CategoryDTO} properties
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultCategoryValidator")
public class DefaultCategoryValidator extends AbstractValidator {

    /**
     * @returns {@link Boolean.TRUE} if the {@code clazz} is equals to "CategoryDTO"
     */
    public boolean supports(final Class<?> clazz) {
        return clazz.equals(CategoryDTO.class);
    }


    /**
     * Validates the {@code obj}
     * 
     * @param obj
     *            the object to be validated
     * @param errors
     *            the errors that the validation will "return"
     */
    public void validate(final Object obj, final Errors errors) {
        if (obj == null || !supports(obj.getClass())) {
            errors.reject(String.format("Object must be of class %s and cannot be null",
                    CategoryDTO.class.getSimpleName()));
            return;
        }
        CategoryDTO category = (CategoryDTO) obj;
        validateNotNull(category.getCode().intern(), category.getCode(), errors);
        validateNotNull(category.getTitle().intern(), category.getTitle(), errors);
    }
}
