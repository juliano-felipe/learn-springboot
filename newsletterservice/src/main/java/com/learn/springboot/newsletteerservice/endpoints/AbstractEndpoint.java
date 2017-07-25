package com.learn.springboot.newsletteerservice.endpoints;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.ResponseMessageDTO.Status;

/**
 * Abstract Endpoint.
 * 
 * @author felipe
 *
 */
public abstract class AbstractEndpoint {

    /**
     * Builds an JSON containing the errors inside {@code errors}.
     * 
     * @param errors
     *            Errors to show on the returning JSON.
     * @return ResponseMessageDTO containing the error messages.
     */
    protected ResponseMessageDTO handleErrors(final List<String> errors) {
        ResponseMessageDTO response = new ResponseMessageDTO();
        response.setStatus(Status.ERROR);
        response.setMessage(StringUtils.join(errors, "; "));
        return response;
    }
}
