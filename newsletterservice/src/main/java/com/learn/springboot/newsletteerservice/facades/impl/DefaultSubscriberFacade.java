package com.learn.springboot.newsletteerservice.facades.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.facades.SubscriberFacade;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;
import com.learn.springboot.newsletteerservice.services.CategoryService;
import com.learn.springboot.newsletteerservice.services.SubscriberService;

/**
 * Manages Subscribers
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultSubscriberFacade")
public class DefaultSubscriberFacade implements SubscriberFacade {

    @Autowired
    @Qualifier("defaultSubscriberService")
    private SubscriberService subscriberService;
    @Autowired
    @Qualifier("defaultCategoryService")
    private CategoryService categoryService;


    /**
     * Saves the Subscriber {@code model}.
     * 
     * @param model
     *            the object to be persisted on the database.
     * @return
     */
    @Override
    public SubscriberModel save(SubscriberModel model) {
        return getSubscriberService().save(model);
    }


    /**
     * Gets all Subscribers on the database
     * 
     * @return a list containing all Subscribers
     */
    @Override
    public List<SubscriberModel> getAllSubscribers() {
        return getSubscriberService().getAllSubscribers();
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
        return getSubscriberService().findOneByEmail(email);
    }


    /**
     * Converts the {@code dto} into a {@link SubscriberModel}
     * 
     * @param subscriber
     *            the object to be converted into a {@link SubscriberModel}
     * @return the {@code dto} converted into a {@link SubscriberModel}
     */
    @Override
    public SubscriberModel convertDTO(SubscriberDTO subscriber) {
        SubscriberModel model = findOneByEmail(subscriber.getEmail());
        if (model == null) {
            model = new SubscriberModel();
        }
        model.setEmail(subscriber.getEmail());
        model.setCategories(getSuperCategories(subscriber));
        return model;
    }


    /**
     * Gets all the Categories, and its parent Categories, that a Subscriber is
     * interested in.
     * 
     * @param subscriber
     *            the Subscriber that has the Categories he's/she's interested in.
     * @return a Set containing the Categories that the subscriber is interested in.
     */
    protected Set<CategoryModel> getSuperCategories(SubscriberDTO subscriber) {
        final Set<CategoryModel> superCategories = new HashSet<CategoryModel>();
        for (CategoryDTO category : subscriber.getCategoryCodes()) {
            final CategoryModel superCategory = getCategoryService()
                    .findOneByCode(category.getCode());
            if (superCategory != null) {
                superCategories.add(superCategory);
            }
        }
        return superCategories;
    }


    protected SubscriberService getSubscriberService() {
        return subscriberService;
    }


    public void setSubscriberService(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }


    protected CategoryService getCategoryService() {
        return categoryService;
    }


    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
