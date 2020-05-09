package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.dto.ReducedBook;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static com.softuni.springintroex.constants.GlobalConstants.BOOKS_FILE_PATH;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        List<String> fileContent = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        fileContent.forEach(r -> {
            String[] data = r.split("\\s+");

            Author author = this.authorService.getRandomAuthor();

            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate = LocalDate.parse(data[1], formatter);

            int copies = Integer.parseInt(data[2]);

            BigDecimal price = new BigDecimal(data[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];

            String title = this.getTitle(data);

            Set<Category> categories = this.categoryService.getRandomCategories();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public List<Book> getBooksByAgeRestriction(String ageRestriction) {
        return this.bookRepository
                .findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> getBooksByEditionTypeAndCopies(String editionType, int copies) {
        return this.bookRepository
                .findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType.toUpperCase()), copies);
    }

    @Override
    public List<Book> getBooksByPriceBoundaries(BigDecimal lessThan, BigDecimal greaterThan) {
        return this.bookRepository
                .findAllByPriceLessThanOrPriceGreaterThan(lessThan, greaterThan);
    }

    @Override
    public List<Book> getBooksByReleaseDateNot(LocalDate before, LocalDate after) {
        return this.bookRepository
                .findAllByReleaseDateBeforeOrReleaseDateAfter(before, after);
    }

    @Override
    public List<Book> getBooksByReleasedDateBefore(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return this.bookRepository
                .findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<Book> getBooksByTitleContaining(String pattern) {
        return this.bookRepository.findAllByTitleContaining(pattern.toLowerCase());
    }

    @Override
    public List<Book> getBooksByAuthorLastNamePattern(String pattern) {
        return this.bookRepository
                .findAllByAuthorLastNameStartingWith(pattern);
    }

    @Override
    public int getBooksCountByTitleLength(int length) {
        return this.bookRepository
                .findAllByTitleLongerThan(length);
    }

    @Override
    public ReducedBook reducedBookByTitle(String title) {
        Book book = this.bookRepository
                .findBookByTitle(title);

        return new ReducedBook(book.getTitle(), book.getEditionType().name(),
                book.getAgeRestriction().name(), book.getPrice());
    }

    @Override
    public int updateBooksCopiesAfterDate(String date, int copies) {
        LocalDate releasedDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy"));

        return this.bookRepository
                .updateAllBooksAfterDate(releasedDate, copies);
    }


    private String getTitle(String[] data) {
        StringBuilder builder = new StringBuilder();

        for (int i = 5; i < data.length; i++) {
            builder.append(data[i]).append(" ");
        }

        return builder.toString().trim();
    }
}
