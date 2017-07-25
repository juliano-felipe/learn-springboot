package com.learn.springboot.newsletteerservice.facades.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.NewsletterDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.NotificationDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.SubscriberDTO;
import com.learn.springboot.newsletteerservice.facades.CategoryFacade;
import com.learn.springboot.newsletteerservice.facades.NewsletterFacade;
import com.learn.springboot.newsletteerservice.models.BookModel;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;
import com.learn.springboot.newsletteerservice.services.BookService;
import com.learn.springboot.newsletteerservice.services.SubscriberService;

/**
 * Manages the Newsletters
 * 
 * @author felipe
 *
 */
@Component
@Qualifier("defaultNewsletterFacade")
public class DefaultNewsletterFacade implements NewsletterFacade {

    @Autowired
    @Qualifier("defaultSubscriberService")
    private SubscriberService subcriberService;
    @Autowired
    @Qualifier("defaultBookService")
    private BookService bookService;
    @Autowired
    @Qualifier("defaultCategoryFacade")
    private CategoryFacade categoryFacade;


    /**
     * Gets all the Newsletters on the database.
     * 
     * @return a list of all Newsletters on the database
     */
    public List<NewsletterDTO> getAllNewsletters() {
        final List<NewsletterDTO> newsletters = new ArrayList<NewsletterDTO>();
        getSubcriberService().getAllSubscribers().stream().forEach(
                s -> {
                    if (CollectionUtils.isNotEmpty(s.getCategories())) {
                        final NewsletterDTO newsletterDTO = new NewsletterDTO();
                        final SubscriberDTO subscriberDTO = new SubscriberDTO(s);
                        newsletterDTO.setSubscriber(subscriberDTO);
                        newsletterDTO.setNotifications(getSubscriberNotifications(s));
                        newsletters.add(newsletterDTO);
                    }
                });
        return newsletters;
    }


    /**
     * Gets the Notifications based on {@code subscribers}
     * 
     * @param subscriber
     *            the {@link SubscriberModel} to get its Notifications.
     * @return a List containing the notifications based on {@code subscribers}
     */
    protected List<NotificationDTO> getSubscriberNotifications(final SubscriberModel subscriber) {
        final List<NotificationDTO> notifications = new ArrayList<>();
        final Set<BookModel> subscriberBooks = getAllBooksForSubscriber(subscriber);
        subscriberBooks.stream().forEach(
                book -> notifications.add(getNewsletterNotitification(book)));
        return notifications;
    }


    /**
     * Gets all the Books from the Categories that the {@code subscriber} is
     * interested in.
     * 
     * @param subscriber
     *            the {@link SubscriberModel} to have its Categories' Books
     *            retrieved
     * @return a set containing the Books that belongs to the Categories that the
     *         {@code subscriber} is interested int.
     */
    protected Set<BookModel> getAllBooksForSubscriber(final SubscriberModel subscriber) {
        final Set<BookModel> books = new HashSet<>();
        for (CategoryModel c : subscriber.getCategories()) {
            books.addAll(getCategoryFacade().getBooks(c));
        }
        return books;
    }


    /**
     * Creates the Notification base on the {@code book}. A notification have the
     * Book title and the full Category path that a given book is contained.
     * 
     * @param book
     *            The Book to create its Notification
     * @return the Notification containing the Book title and its Categories full
     *         path.
     */
    protected NotificationDTO getNewsletterNotitification(final BookModel book) {
        final NotificationDTO notificationDTO = new NotificationDTO();
        final BookDTO bookDTO = new BookDTO(book);
        notificationDTO.setBook(bookDTO);
        notificationDTO.setCategoryPaths(bookDTO.getCategoryCodes());
        return notificationDTO;
    }


    protected SubscriberService getSubcriberService() {
        return subcriberService;
    }


    public void setSubcriberService(SubscriberService subcriberService) {
        this.subcriberService = subcriberService;
    }


    protected BookService getBookService() {
        return bookService;
    }


    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }


    protected CategoryFacade getCategoryFacade() {
        return categoryFacade;
    }


    public void setCategoryFacade(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }
}
