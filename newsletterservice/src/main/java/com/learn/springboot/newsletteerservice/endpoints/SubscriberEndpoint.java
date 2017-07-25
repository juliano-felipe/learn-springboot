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

import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO.Status;
import com.learn.springboot.newsletteerservice.facades.SubscriberFacade;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;

/**
 * Manages Subscribers.
 * 
 * @author felipe
 *
 */
@RestController
@RequestMapping(value = "/subscribers")
public class SubscriberEndpoint extends AbstractEndpoint {

    @Autowired
    @Qualifier("defaultSubscriberFacade")
    private SubscriberFacade subscriberFacade;
    @Autowired
    @Qualifier("defaultSubcriberValidator")
    private Validator subscriberValidator;


    /**
     * 
     * Saves the subscriber {@code subscriberDto} requested by JSON.
     * 
     * @param subscriberDto
     *            The subscriber to be persisted on the database.
     * @return A JSON containing a success message, in case it was successful, or an
     *         error message, otherwise.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseMessageDTO save(@RequestBody final SubscriberDTO subscriberDto,
            BindingResult bindingResult) {
        try {
            getSubscriberValidator().validate(subscriberDto, bindingResult);
            if (bindingResult.hasErrors()) {
                return handleErrors(bindingResult.getAllErrors().stream()
                        .map(e -> e.getCode()).collect(Collectors.toList()));
            }
            SubscriberModel subscriber = getSubscriberFacade().convertDTO(subscriberDto);
            getSubscriberFacade().save(subscriber);
            ResponseMessageDTO response = new ResponseMessageDTO();
            response.setStatus(Status.SUCCESS);
            response.setMessage(String.format("Subscriber with email %s saved.",
                    subscriber.getEmail()));
            return response;
        } catch (Exception e) {
            // Please, don't repeat this heresy. But ____ [god(s) of your faith here] knows
            // why java throws an error when I try to catch the adequate exception for
            // this case...
            return handleErrors(Arrays.asList(e.getMessage()));
        }
    }


    protected SubscriberFacade getSubscriberFacade() {
        return subscriberFacade;
    }


    public void setSubscriberFacade(SubscriberFacade subscriberFacade) {
        this.subscriberFacade = subscriberFacade;
    }


    protected Validator getSubscriberValidator() {
        return subscriberValidator;
    }


    public void setSubscriberValidator(Validator subscriberValidator) {
        this.subscriberValidator = subscriberValidator;
    }
}
