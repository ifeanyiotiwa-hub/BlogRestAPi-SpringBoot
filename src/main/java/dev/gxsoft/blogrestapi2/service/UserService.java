package dev.gxsoft.blogrestapi2.service;


import dev.gxsoft.blogrestapi2.model.User;

public interface UserService {
    String loginUser(String username, String password);
    String registerUser(User user);
    String deactivateUser(User user);
}
