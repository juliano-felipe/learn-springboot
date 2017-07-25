package com.learn.springboot.newsletteerservice.endpoints.dtos.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.collect.Lists;
import com.learn.springboot.newsletteerservice.endpoints.dtos.BookDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.CategoryDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.NewsletterDTO;
import com.learn.springboot.newsletteerservice.endpoints.dtos.NotificationDTO;

/**
 * Serializes a Newsletter into a JSON.
 * 
 * @author felipe
 *
 */
public class NewsletterDTOJsonSerializer extends JsonSerializer<NewsletterDTO> {

    /**
     * Serializes the {@code dto} object into JSON.
     * 
     * @param newsletter
     *            the Newsletter to be serialized into JSON
     * @param generator
     *            the JsonGenerator
     * @param provider
     *            the Serialize provider
     */
    @Override
    public void serialize(NewsletterDTO newsletter, JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {
        generator.writeStartObject();
        generator.writeStringField("recipient", newsletter.getSubscriber().getEmail());
        generator.writeArrayFieldStart("notifications");
        for (NotificationDTO notification : newsletter.getNotifications()) {
            generator.writeStartObject();
            generator.writeObjectField("book", notification.getBook().getTitle());
            writeBookCategories(generator, notification.getBook());
            generator.writeEndObject();
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }


    /**
     * Writes the Categories to the {@code generator} based on the {@code book}
     * 
     * @param generator
     *            the Jsongenerator
     * @param book
     *            the Book DTO containing the Categories to be written on
     *            {@code generator}
     * @throws IOException
     * @throws JsonProcessingException
     */
    protected void writeBookCategories(final JsonGenerator generator, final BookDTO book)
            throws IOException, JsonProcessingException {
        generator.writeArrayFieldStart("categoryPaths");
        for (CategoryDTO category : book.getCategoryCodes()) {
            generator.writeStartArray();
            for (CategoryDTO categoryPath : Lists.reverse(getBookFullCategoryPath(category))) {
                generator.writeString(categoryPath.getTitle());
            }
            generator.writeEndArray();
        }
        generator.writeEndArray();
    }


    /**
     * Gets the full {@code category} path, from the first parent to the
     * {@code category}
     * 
     * @param category
     *            the Category to get its parent Categories;
     * @return a list containing all the Categories from the first parent to the
     *         {@code category}
     */
    private List<CategoryDTO> getBookFullCategoryPath(final CategoryDTO category) {
        final List<CategoryDTO> bookCategories = new ArrayList<>();
        bookCategories.add(category);
        if (category.getSuperCategory() != null) {
            bookCategories.addAll(getBookFullCategoryPath(category.getSuperCategory()));
        }
        return bookCategories;
    }
}
