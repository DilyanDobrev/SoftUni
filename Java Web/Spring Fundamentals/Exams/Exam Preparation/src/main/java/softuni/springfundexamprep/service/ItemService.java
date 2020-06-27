package softuni.springfundexamprep.service;

import softuni.springfundexamprep.model.service.ItemServiceModel;
import softuni.springfundexamprep.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemViewModel> getAllItems();

    ItemViewModel getById(String id);

    void delete(String id);
}
