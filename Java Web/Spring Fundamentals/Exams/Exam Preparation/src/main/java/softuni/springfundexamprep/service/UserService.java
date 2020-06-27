package softuni.springfundexamprep.service;

import softuni.springfundexamprep.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel getUserByUsername(String username);
}
