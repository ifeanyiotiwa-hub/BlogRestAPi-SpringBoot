package dev.gxsoft.blogrestapi2.service;


import antlr.collections.impl.LList;
import dev.gxsoft.blogrestapi2.model.User;

import java.util.List;

public interface UserService {
    User loginUser(String username, String password);
    String registerUser(User user);
    String deactivateUser(User user);
    User findUserById(long id);
    User updateUser(User user);
    List<User> getAllUsers();
    void deleteAllUsers();
}
