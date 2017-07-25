package com.learn.springboot.newsletteerservice.endpoints.dtos;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learn.springboot.newsletteerservice.models.CategoryModel;
import com.learn.springboot.newsletteerservice.models.SubscriberModel;

/**
 * The Subscriber DTO, also mapped to be de-serializable to JSON.
 * 
 * @author felipe
 *
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "email")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriberDTO {

    private String email;
    private Set<CategoryDTO> categoryCodes;


    /**
     * 
     */
    public SubscriberDTO() {
        //
    }


    public SubscriberDTO(final SubscriberModel subscriberModel) {
        this.setEmail(subscriberModel.getEmail());
        this.categoryCodes = new HashSet<>();
        for (CategoryModel c : subscriberModel.getCategories()) {
            this.categoryCodes.add(new CategoryDTO(c));
        }
    }


    /**
     * 
     * @param props
     */
    @SuppressWarnings("unchecked")
    @JsonCreator
    public SubscriberDTO(Map<String, Object> props) {
        this.email = String.valueOf(props.get("email"));
        final Set<CategoryDTO> superCategories = new HashSet<CategoryDTO>();
        for (String categoryCode : ((List<String>) props.get("categoryCodes"))) {
            final CategoryDTO superCategory = new CategoryDTO();
            superCategory.setCode(categoryCode);
            superCategories.add(superCategory);
        }
        this.categoryCodes = new HashSet<CategoryDTO>();
        this.categoryCodes.addAll(superCategories);
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Set<CategoryDTO> getCategoryCodes() {
        return categoryCodes;
    }


    public void setCategoryCodes(Set<CategoryDTO> categoryCodes) {
        this.categoryCodes = categoryCodes;
    }
}
