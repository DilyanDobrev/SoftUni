package com.softuni.springintroex.controllers;

import com.softuni.springintroex.dto.ReducedBook;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader reader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader reader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.reader = reader;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        // Tasks
        String input;

        while (true) {
            System.out.println("Type 1 - 11 or 0 to end the program");
            input = this.reader.readLine();

            switch (input) {
                case "0":
                    return;
                case "1":
                    printBooksByAgeRestriction();
                    break;
                case "2":
                    printGoldenEditionBooksUnder5000Copies();
                    break;
                case "3":
                    printBooksByPriceBoundaries();
                    break;
                case "4":
                    printBooksNotReleasedIn();
                    break;
                case "5":
                    printBooksReleasedBefore();
                    break;
                case "6":
                    printAuthorsWithFirstNameEnding();
                    break;
                case "7":
                    printBooksByTitleContaining();
                    break;
                case "8":
                    //printAuthorsAndBooksByPattern();
                    printBooksByAuthorLastNameStartsWith();
                    break;
                case "9":
                    printBooksWithTitleLengthGreaterThan();
                    break;
                case "10":
                    printAuthorsByTotalBookCopies();
                    break;
                case "11":
                    printReducedBook();
                    break;
                case "12":
                    printBooksCopiesAdded();
                    break;
                default:
                    System.out.println("I said 1 - 11!!!");
                    break;
            }

            taskSeparator();
        }
    }

    private void printBooksCopiesAdded() throws IOException {
        System.out.println("Enter date and number of copies.");

        String date = this.reader.readLine();
        int copies = Integer.parseInt(this.reader.readLine());

        int totalCopies = this.bookService
                .updateBooksCopiesAfterDate(date, copies) * copies;

        System.out.println(totalCopies);
    }

    private void printReducedBook() throws IOException {
        System.out.println("Enter book title.");

        String title = this.reader.readLine();

        ReducedBook book = this.bookService
                .reducedBookByTitle(title);

        System.out.printf("%s %s %s %.2f%n",
                book.getTitle(),
                book.getEditionType(),
                book.getAgeRestriction(),
                book.getPrice());
    }

    private void printAuthorsByTotalBookCopies() {
        this.authorService
                .getAuthorsByTotalBookCopies()
                .forEach(o -> System.out.println(o[0] + " - " + o[1]));
    }

    private void printBooksWithTitleLengthGreaterThan() throws IOException {
        System.out.println("Enter length.");

        int length = Integer.parseInt(this.reader.readLine());

        System.out.println(this.bookService
                .getBooksCountByTitleLength(length));
    }

    private void printBooksByAuthorLastNameStartsWith() throws IOException {

        System.out.println("Enter pattern.");

        String pattern = this.reader.readLine();

        this.bookService
                .getBooksByAuthorLastNamePattern(pattern)
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                        b.getTitle(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()));
    }

   /*
    private void printAuthorsAndBooksByPattern() throws IOException {

        System.out.println("Enter pattern.");

        String pattern = this.reader.readLine();

        this.authorService
                .getAuthorsByLastNameStarts(pattern)
                .forEach(a -> {
                    a.getBooks()
                            .forEach(b -> System.out.printf("%s (%s %s)%n",
                                    b.getTitle(),
                                    b.getAuthor().getFirstName(),
                                    b.getAuthor().getLastName()));
                });
    }
    */

    private void printBooksByTitleContaining() throws IOException {
        System.out.println("Enter pattern.");

        String pattern = this.reader.readLine();

        this.bookService
                .getBooksByTitleContaining(pattern)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printAuthorsWithFirstNameEnding() throws IOException {
        System.out.println("Enter pattern.");

        String endsWith = this.reader.readLine();

        this.authorService
                .getAuthorsWithFirstNameEnding(endsWith)
                .forEach(a -> System.out.printf("%s %s%n",
                        a.getFirstName(),
                        a.getLastName()));
    }

    private void printBooksReleasedBefore() throws IOException {
        System.out.println("Enter date.");

        String date = this.reader.readLine();

        this.bookService
                .getBooksByReleasedDateBefore(date)
                .forEach(b -> System.out.printf("%s %s %.2f%n",
                        b.getTitle(),
                        b.getEditionType().name(),
                        b.getPrice()));
    }

    private void printBooksNotReleasedIn() throws IOException {
        System.out.println("Enter year.");

        int year = Integer.parseInt(this.reader.readLine());
        this.bookService
                .getBooksByReleaseDateNot(
                        LocalDate.of(year, 1, 1),
                        LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printBooksByPriceBoundaries() {
        this.bookService
                .getBooksByPriceBoundaries(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .forEach(b -> System.out.printf("%s - $%.2f%n",
                        b.getTitle(),
                        b.getPrice()));
    }

    private void printGoldenEditionBooksUnder5000Copies() {
        this.bookService
                .getBooksByEditionTypeAndCopies("gold", 5000)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printBooksByAgeRestriction() throws IOException {
        System.out.println("Enter age restriction.");

        this.bookService.getBooksByAgeRestriction(this.reader.readLine())
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void taskSeparator() {
        System.out.println("***********************************");
    }
}
