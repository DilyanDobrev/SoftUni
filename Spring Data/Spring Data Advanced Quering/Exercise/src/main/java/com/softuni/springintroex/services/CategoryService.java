package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;

    Category getCategoryById(Long id);

    Set<Category> getRandomCategories();
}
