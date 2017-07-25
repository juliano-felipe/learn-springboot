package com.learn.springboot.newsletteerservice.facades;

import java.util.List;

import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;

/**
 * Manages Subscribers
 * 
 * @author felipe
 *
 */
public interface SubscriberFacade {

    /**
     * Saves the Subscriber {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    SubscriberModel save(SubscriberModel model);


    /**
     * Gets all Subscribers on the database
     * 
     * @return a list containing all Subscribers
     */
    List<SubscriberModel> getAllSubscribers();


    /**
     * Retrieves a Subscriber from the databse based on the {@code email}
     * 
     * @param email
     *            the value to search on the database
     * 
     * @return a {@link SubscriberModel} that belongs to the {@code email}
     */
    SubscriberModel findOneByEmail(final String email);


    /**
     * Converts the {@code dto} into a {@link SubscriberModel}
     * 
     * @param dto
     *            the object to be converted into a {@link SubscriberModel}
     * @return the {@code dto} converted into a {@link SubscriberModel}
     */
    SubscriberModel convertDTO(SubscriberDTO dto);
}
