package com.learn.springboot.newsletteerservice.endpoints.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learn.springboot.newsletteerservice.endpoints.dtos.serializers.NewsletterDTOJsonSerializer;

/**
 * The Newsletter DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "subscriber")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = NewsletterDTOJsonSerializer.class)
public class NewsletterDTO {

    @JsonIgnoreProperties(value = { "categoryCodes" })
    private SubscriberDTO subscriber;
    private List<NotificationDTO> notifications;


    public SubscriberDTO getSubscriber() {
        return subscriber;
    }


    public void setSubscriber(SubscriberDTO subscriber) {
        this.subscriber = subscriber;
    }


    public List<NotificationDTO> getNotifications() {
        return notifications;
    }


    public void setNotifications(List<NotificationDTO> notifications) {
        this.notifications = notifications;
    }
}
