package softuni.springfundexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.springfundexamprep.model.entity.Item;
import softuni.springfundexamprep.model.service.CategoryServiceModel;
import softuni.springfundexamprep.model.service.ItemServiceModel;
import softuni.springfundexamprep.model.view.ItemViewModel;
import softuni.springfundexamprep.repository.ItemRepository;
import softuni.springfundexamprep.service.CategoryService;
import softuni.springfundexamprep.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        CategoryServiceModel categoryServiceModel = this.categoryService
                .getByCategoryName(itemServiceModel.getCategory().getCategoryName());

        itemServiceModel.setCategory(categoryServiceModel);

        this.itemRepository.saveAndFlush(this.modelMapper.map(itemServiceModel, Item.class));
    }

    @Override
    public List<ItemViewModel> getAllItems() {

        return this.itemRepository
                .findAll()
                .stream()
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
                    itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",
                            item.getGender(), item.getCategory().getCategoryName().name()));
                    return itemViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ItemViewModel getById(String id) {
        return this.itemRepository
                .findById(id)
                .map(item -> {
                    ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
                    itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg",
                            item.getGender(), item.getCategory().getCategoryName().name()));
                    return itemViewModel;
                })
                .orElse(null);
    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }
}
