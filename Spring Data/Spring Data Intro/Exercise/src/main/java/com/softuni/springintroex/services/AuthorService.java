package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    int getAuthorsCount();

    Author findAuthorById(Long id);

    Author getRandomAuthor();

    List<Author> getAuthorsWithFirstNameEnding(String endsWith);

    //List<Author> getAuthorsByLastNameStarts(String pattern);

    List<Object[]> getAuthorsByTotalBookCopies();
}
