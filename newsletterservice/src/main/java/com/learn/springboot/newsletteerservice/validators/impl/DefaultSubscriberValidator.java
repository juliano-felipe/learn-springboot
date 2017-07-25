package com.learn.springboot.newsletteerservice.validators.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.validators.AbstractValidator;

/**
 * Validates a {@link SubscriberDTO} properties
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultSubcriberValidator")
public class DefaultSubscriberValidator extends AbstractValidator {

    /**
     * @returns {@link Boolean.TRUE} if the {@code clazz} is equals to
     *          "SubscriberDTO"
     */
    public boolean supports(final Class<?> clazz) {
        return clazz.equals(SubscriberDTO.class);
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
                    SubscriberDTO.class.getSimpleName()));
            return;
        }
        SubscriberDTO subscriber = (SubscriberDTO) obj;
        validateNotNull(subscriber.getEmail().intern(), subscriber.getEmail(), errors);
        validateNotNull("categories", subscriber.getCategoryCodes(), errors);
        for (CategoryDTO categoryDto : subscriber.getCategoryCodes()) {
            validateCategoryExists(categoryDto.getCode(), errors);
        }
    }
}
