package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.softuni.springintroex.constants.GlobalConstants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {

        if (this.categoryRepository.count() != 0) {
            return;
        }

        List<String> fileContent = this.fileUtil.readFileContent(CATEGORIES_FILE_PATH);

        fileContent.forEach(r -> {
            Category category = new Category(r);

            this.categoryRepository.saveAndFlush(category);
        });
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public Set<Category> getRandomCategories() {
        Random random = new Random();

        int bound = random.nextInt(3) + 1;

        Set<Category> result = new HashSet<>();
        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.getCategoryById((long) categoryId));
        }

        return result;
    }
}
