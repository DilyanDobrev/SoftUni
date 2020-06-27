package softuni.springfundexamprep.service;

import softuni.springfundexamprep.model.entity.CategoryName;
import softuni.springfundexamprep.model.service.CategoryServiceModel;

public interface CategoryService {
    void initCategories();

    CategoryServiceModel getByCategoryName(CategoryName categoryName);
}
