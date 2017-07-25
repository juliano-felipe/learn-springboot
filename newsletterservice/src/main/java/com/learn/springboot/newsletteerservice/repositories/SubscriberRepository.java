package com.learn.springboot.newsletteerservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.learn.springboot.newsletteerservice.models.SubscriberModel;

/**
 * Retrieves data from the database regarding Subscribers
 * 
 * @author felipe
 *
 */
public interface SubscriberRepository
        extends JpaRepository<SubscriberModel, Long>, QueryByExampleExecutor<SubscriberModel> {
}
