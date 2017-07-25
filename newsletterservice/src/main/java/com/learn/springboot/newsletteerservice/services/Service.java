package com.learn.springboot.newsletteerservice.services;

/**
 * 
 * @author felipe
 *
 */
public interface Service<MODEL, DTO> {

    /**
     * Persists the {@code model} on the database.
     * 
     * @param model
     *            the object to be persisted on the database
     */
    MODEL save(final MODEL model);
}
