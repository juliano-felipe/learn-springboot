package com.learn.springboot.newsletteerservice.endpoints.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The ResponseMessage DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMessageDTO {

    private Status status;
    private String message;


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * The possible status a response message can have.
     * 
     * @author felipe
     *
     */
    public enum Status {
        SUCCESS("success"), ERROR("error");

        private String name;


        private Status(final String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }
    }
}
