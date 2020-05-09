package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static com.softuni.springintroex.constants.GlobalConstants.AUTHORS_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {

        if (this.authorRepository.count() != 0) {
            return;
        }

        List<String> fileContent = this.fileUtil.readFileContent(AUTHORS_FILE_PATH);

        fileContent.forEach(r -> {
            String[] data = r.split("\\s+");
            Author author = new Author(data[0], data[1]);

            this.authorRepository.saveAndFlush(author);
        });
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.getAuthorsCount()) + 1;

        return this.findAuthorById((long) randomId);
    }

    @Override
    public List<Author> getAuthorsWithFirstNameEnding(String endsWith) {
        return this.authorRepository
                .findAllByFirstNameEndingWith(endsWith);
    }

    @Override
    public List<Object[]> getAuthorsByTotalBookCopies() {
        return this.authorRepository
                .findAllByTotalBookCopies();
    }

//    @Override
//    public List<Author> getAuthorsByLastNameStarts(String pattern) {
//        return this.authorRepository
//                .findAllByLastNameStartsWith(pattern.toLowerCase());
//    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }
}
