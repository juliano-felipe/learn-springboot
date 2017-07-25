package com.learn.springboot.newsletteerservice.validators.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.validators.AbstractValidator;

/**
 * Validates a {@link BookDTO} properties
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultBookValidator")
public class DefaultBookValidator extends AbstractValidator {

    /**
     * @returns {@link Boolean.TRUE} if the {@code clazz} is equals to "BookDTO"
     */
    public boolean supports(final Class<?> clazz) {
        return clazz.equals(BookDTO.class);
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
                    BookDTO.class.getSimpleName()));
            return;
        }
        BookDTO book = (BookDTO) obj;
        validateNotNull(book.getTitle().intern(), book.getTitle(), errors);
        validateNotNull("superCategories", book.getCategoryCodes(), errors);
        for (CategoryDTO categoryDTO : book.getCategoryCodes()) {
            validateCategoryExists(categoryDTO.getCode(), errors);
        }
    }
}
