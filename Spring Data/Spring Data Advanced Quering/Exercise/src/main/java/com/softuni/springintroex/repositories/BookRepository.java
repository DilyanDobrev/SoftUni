package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContaining(String pattern);

    @Query("SELECT b FROM Book b WHERE b.author.lastName LIKE CONCAT(:pattern, '%')")
    List<Book> findAllByAuthorLastNameStartingWith(@Param("pattern") String pattern);

    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :length")
    int findAllByTitleLongerThan(@Param("length") int length);

    Book findBookByTitle(String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateAllBooksAfterDate(@Param("date") LocalDate date, @Param("copies") int copies);
}
