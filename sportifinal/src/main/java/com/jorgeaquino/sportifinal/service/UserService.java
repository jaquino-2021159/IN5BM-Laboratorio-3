package com.jorgeaquino.sportifinal.service;

import com.jorgeaquino.sportifinal.model.User;
import java.util.List;

public interface UserService {
    List<User>getAllUser();
    User getUserById(Integer id);
    User saveUser(User user);
    User updateUser(Integer id,User user);
    void deleteUser(Integer id);
}
