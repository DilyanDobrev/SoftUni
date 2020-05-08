package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAllByFirstNameEndingWith(String endsWith);

    //List<Author> findAllByLastNameStartsWith(String pattern);

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName), " +
            "SUM(b.copies) FROM Author a JOIN Book b " +
            "ON a.id = b.author.id GROUP BY CONCAT(a.firstName, ' ', a.lastName) " +
            "ORDER BY SUM(b.copies) DESC")
    List<Object[]> findAllByTotalBookCopies();
}
