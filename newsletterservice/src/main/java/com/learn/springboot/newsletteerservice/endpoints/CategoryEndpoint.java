package com.learn.springboot.newsletteerservice.endpoints;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO.Status;
import com.learn.springboot.newsletteerservice.facades.CategoryFacade;
import com.learn.springboot.newsletteerservice.models.CategoryModel;

/**
 * Manages Categories.
 * 
 * @author felipe
 *
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryEndpoint extends AbstractEndpoint {

    @Autowired
    @Qualifier("defaultCategoryFacade")
    private CategoryFacade categoryFacade;
    @Autowired
    @Qualifier("defaultCategoryValidator")
    private Validator categoryValidator;


    /**
     * 
     * Saves the category {@code categoryDto} requested by JSON.
     * 
     * @param categoryDto
     *            The category to be persisted on the database.
     * @return A JSON containing a success message, in case it was successful, or an
     *         error message, otherwise.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseMessageDTO save(@RequestBody final CategoryDTO categoryDto,
            BindingResult bindingResult) {
        try {
            getCategoryValidator().validate(categoryDto, bindingResult);
            if (bindingResult.hasErrors()) {
                return handleErrors(bindingResult.getAllErrors().stream()
                        .map(e -> e.getCode()).collect(Collectors.toList()));
            }
            CategoryModel category = getCategoryFacade().convertDTO(categoryDto);
            getCategoryFacade().save(category);
            ResponseMessageDTO response = new ResponseMessageDTO();
            response.setStatus(Status.SUCCESS);
            response.setMessage(String.format("Category with code %s saved.",
                    category.getCode()));
            return response;
        } catch (Exception e) {
            // Please, don't repeat this heresy. But ____ [god(s) of your faith here] knows
            // why java throws an error when I try to catch the adequate exception for
            // this case...
            return handleErrors(Arrays.asList(e.getMessage()));
        }
    }


    protected CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }


    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }


    protected Validator getCategoryValidator() {
        return categoryValidator;
    }


    public void setCategoryValidator(Validator categoryValidator) {
        this.categoryValidator = categoryValidator;
    }
}
