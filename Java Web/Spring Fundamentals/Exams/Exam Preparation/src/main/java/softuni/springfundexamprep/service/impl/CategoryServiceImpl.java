package softuni.springfundexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.springfundexamprep.model.entity.Category;
import softuni.springfundexamprep.model.entity.CategoryName;
import softuni.springfundexamprep.model.service.CategoryServiceModel;
import softuni.springfundexamprep.repository.CategoryRepository;
import softuni.springfundexamprep.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository.save(new Category(categoryName,
                                String.format("Description for %s",
                                        categoryName.name())));
                    });

        }
    }

    @Override
    public CategoryServiceModel getByCategoryName(CategoryName categoryName) {
        return this.categoryRepository
                .findByCategoryName(categoryName)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }
}
