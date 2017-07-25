package com.learn.springboot.newsletteerservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.learn.springboot.newsletteerservice.models.SubscriberModel;
import com.learn.springboot.newsletteerservice.repositories.CategoryRepository;
import com.learn.springboot.newsletteerservice.repositories.SubscriberRepository;
import com.learn.springboot.newsletteerservice.services.SubscriberService;

/**
 * Manages Subscribers
 * 
 * @author felipe
 *
 */
@Service
@Qualifier("defaultSubscriberService")
public class DefaultSubscriberService implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Persists the {@code model} on the database
     */
    @Override
    public SubscriberModel save(SubscriberModel model) {
        return getSubscriberRepository().save(model);
    }


    /**
     * Gets all the subscribers on the database
     * 
     * @return a List containing all the Subscribers on the database
     */
    @Override
    public List<SubscriberModel> getAllSubscribers() {
        return getSubscriberRepository().findAll();
    }


    /**
     * Retrieves a Subscriber from the databse based on the {@code email}
     * 
     * @param email
     *            the value to search on the database
     * 
     * @return a {@link SubscriberModel} that belongs to the {@code email}
     */
    @Override
    public SubscriberModel findOneByEmail(final String email) {
        final SubscriberModel subscriberExample = new SubscriberModel();
        subscriberExample.setEmail(email);
        final Example<SubscriberModel> example = Example.of(subscriberExample);
        return getSubscriberRepository().findOne(example);
    }


    protected SubscriberRepository getSubscriberRepository() {
        return subscriberRepository;
    }


    public void setSubscriberRepository(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }


    protected CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }


    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
