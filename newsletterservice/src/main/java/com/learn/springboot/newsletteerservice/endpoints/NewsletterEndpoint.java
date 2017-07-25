package com.learn.springboot.newsletteerservice.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springboot.newsletteerservice.endpoints.dtos.NewsletterDTO;
import com.learn.springboot.newsletteerservice.facades.NewsletterFacade;

/**
 * Provides informations regarding Newsletters.
 * 
 * @author felipe
 *
 */
@RestController
@RequestMapping(value = "/newsletters")
public class NewsletterEndpoint extends AbstractEndpoint {

    @Autowired
    @Qualifier("defaultNewsletterFacade")
    private NewsletterFacade newsletterFacade;


    /**
     * Returns a JSON containing a list of Subscribers and its respective Book, and
     * its Categories, that a Subscriber is interested in.
     * 
     * @return a JSON containing informations about all Subscribers.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<NewsletterDTO> getNewsletters() {
        List<NewsletterDTO> newsletters = new ArrayList<NewsletterDTO>();
        newsletters = getNewsletterFacade().getAllNewsletters();
        return newsletters;
    }


    protected NewsletterFacade getNewsletterFacade() {
        return newsletterFacade;
    }


    public void setNewsletterFacade(NewsletterFacade newsletterFacade) {
        this.newsletterFacade = newsletterFacade;
    }
}
