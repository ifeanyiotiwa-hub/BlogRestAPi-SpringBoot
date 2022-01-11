package dev.gxsoft.blogrestapi2.controller;


import dev.gxsoft.blogrestapi2.dto.LoggedInUser;
import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/api")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final UserService userService;

    public BlogController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String indexPage() {
        return "Welcome to Ifeanyichukwu Otiwa Blog RESTAPI!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        var resp = userService.registerUser(user);
        return resp != null ? resp : "Error registering User";
    }


    @PostMapping("/login")
    public User loginUser(@RequestBody LoggedInUser user)  {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

}
