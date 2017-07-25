package com.learn.springboot.newsletteerservice.facades;

import java.util.List;

import com.learn.springboot.newsletteerservice.endpoints.dtos.NewsletterDTO;

/**
 * Manages the Newsletters
 * 
 * @author felipe
 *
 */
public interface NewsletterFacade {

    /**
     * Gets all the Newsletters on the database.
     * 
     * @return a list of all Newsletters on the database
     */
    List<NewsletterDTO> getAllNewsletters();
}
