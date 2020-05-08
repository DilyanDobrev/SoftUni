package com.softuni.springintroex.services;

import com.softuni.springintroex.dto.ReducedBook;
import com.softuni.springintroex.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> getBooksByAgeRestriction(String ageRestriction);

    List<Book> getBooksByEditionTypeAndCopies(String editionType, int copies);

    List<Book> getBooksByPriceBoundaries(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> getBooksByReleaseDateNot(LocalDate before, LocalDate after);

    List<Book> getBooksByReleasedDateBefore(String date);

    List<Book> getBooksByTitleContaining(String pattern);

    List<Book> getBooksByAuthorLastNamePattern(String pattern);

    int getBooksCountByTitleLength(int length);

    ReducedBook reducedBookByTitle(String title);

    int updateBooksCopiesAfterDate(String date, int copies);
}
