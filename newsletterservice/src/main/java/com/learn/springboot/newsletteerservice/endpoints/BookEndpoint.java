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

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO.Status;
import com.learn.springboot.newsletteerservice.facades.BookFacade;
import com.learn.springboot.newsletteerservice.models.BookModel;

/**
 * Manages Books.
 * 
 * @author felipe
 *
 */
@RestController
@RequestMapping(value = "/books")
public class BookEndpoint extends AbstractEndpoint {

    @Autowired
    @Qualifier("defaultBookFacade")
    private BookFacade bookFacade;
    @Autowired
    @Qualifier("defaultBookValidator")
    private Validator bookValidator;


    /**
     * Saves the book {@code bookDto} requested by JSON.
     * 
     * @param bookDto
     *            The book to be persisted on the database.
     * @return A JSON containing a success message, in case it was successful, or an
     *         error message, otherwise.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseMessageDTO save(@RequestBody final BookDTO bookDto,
            BindingResult bindingResult) {
        try {
            getBookValidator().validate(bookDto, bindingResult);
            if (bindingResult.hasErrors()) {
                return handleErrors(bindingResult.getAllErrors().stream()
                        .map(e -> e.getCode()).collect(Collectors.toList()));
            }
            final BookModel book = getBookFacade().convertDTO(bookDto);
            getBookFacade().save(book);
            ResponseMessageDTO response = new ResponseMessageDTO();
            response.setStatus(Status.SUCCESS);
            response.setMessage(String.format("Book with title %s saved.", book.getTitle()));
            return response;
        } catch (Exception e) {
            // Please, don't repeat this heresy. But ____ [god(s) of your faith here] knows
            // why java throws an error when I try to catch the adequate exception for
            // this case...
            return handleErrors(Arrays.asList(e.getMessage()));
        }
    }


    protected BookFacade getBookFacade() {
        return bookFacade;
    }


    public void setBookFacade(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }


    protected Validator getBookValidator() {
        return bookValidator;
    }


    public void setBookValidator(Validator bookValidator) {
        this.bookValidator = bookValidator;
    }
}
