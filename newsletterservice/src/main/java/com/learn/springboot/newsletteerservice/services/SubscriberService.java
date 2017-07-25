package com.learn.springboot.newsletteerservice.services;

import java.util.List;

import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;

/**
 * Manages Subscribers
 * 
 * @author felipe
 *
 */
public interface SubscriberService extends Service<SubscriberModel, SubscriberDTO> {

    /**
     * Gets all the subscribers on the database
     * 
     * @return a List containing all the Subscribers on the database
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
}
