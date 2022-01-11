package dev.gxsoft.blogrestapi2.controller;


import dev.gxsoft.blogrestapi2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/api")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    @GetMapping("")
    public String indexPage() {
        return "Welcome to Ifeanyichukwu Otiwa Blog RESTAPI!";
    }

    @PostMapping("/register")
    public String saveUser(User user) {
        return "User saved";
    }
}
