package dev.gxsoft.blogrestapi2.controller;


import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.dto.LoggedInUser;
import dev.gxsoft.blogrestapi2.dto.PostDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;
import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.service.CommentService;
import dev.gxsoft.blogrestapi2.service.PostService;
import dev.gxsoft.blogrestapi2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/blog/api")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    public BlogController(UserService userService,
                          PostService postService,
                          CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("")
    public String indexPage() {
        logger.info("List of User in DB");
        return "Welcome to Ifeanyichukwu Otiwa Blog RESTAPI!";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable long userId) {
        return userService.findUserById(userId);
    }

    // -------------------------------------------------------------------------------------------------
    //USER CRUD OPERATIONS
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        var resp = userService.registerUser(user);
        return resp != null ? resp : "Error registering User";
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoggedInUser user)  {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable long userId) {
        var userT = userService.findUserById(userId);
        return userService.deactivateUser(userT) ;
    }

    @PutMapping("/Update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/users")
    public String clearUser() {
        userService.deleteAllUsers();
        return "Cleared";
    }

    // -----------------------------------------------------------------------------
    //POST CRUD OPERATIONS

    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getAllPostByUserId(@PathVariable long userId) {
        return postService.getAllPostByUserId(userId);
    }

    @PostMapping("/{userId}/post") //userId is Id of user making post
    public Post createPost(@PathVariable long userId, @RequestBody PostDTO postDTO) {
        var user = userService.findUserById(userId);
        if (user != null){
            var post = new Post();
            logger.info(user.toString());
            post.setTitle(postDTO.getTitle());
            post.setBody(postDTO.getBody());
            post.setUserId(userId);
            return postService.savePost(post);
        } else {
            logger.info("User not found");
            throw new RuntimeException("User not found");
        }
    }

    @PutMapping("/{userId}/{postId}/")
    public String updatePost(@PathVariable long userId, @PathVariable long postId, @RequestBody PostDTO postDTO) {
        var user = userService.findUserById(userId);
        var post = postService.getPost(postId);
        Objects.requireNonNull(user);
        Objects.requireNonNull(post);
        if (user.getUserId() == post.getUserId()) {
            postService.updatePost(new Post(userId, postDTO.getTitle(), postDTO.getBody()));
            return "Post Updated";
        } else {
            return "something went wrong";
        }
    }

    @DeleteMapping("/{userId}/{postId}")
    public void deletePost(@PathVariable long postId, @PathVariable long userId) {
        var user = userService.findUserById(userId);
        var post = postService.getPost(postId);
        Objects.requireNonNull(user);
        Objects.requireNonNull(post);
        if (post.getUserId() == user.getUserId()){
            postService.deletePost(postId);
            logger.debug("Post deleted");
        } else {
            logger.info("post did not delete successfully");
        }
    }

    //======================================================================================================
    //COMMENTS CRUD

    @GetMapping("/{userId}/{postId}/comments")
    public List<Comment> getPostComments(@PathVariable long userId, @PathVariable long postId) {
        return commentService.getAllCommentsOfPost(postId, userId);
    }

    @PostMapping("/{userId}/comment") // userID is Id of the commenting user
    public Comment createComment(@PathVariable long userId, @RequestBody CommentDTO comment) {
        var post = postService.getPostById(comment.getPostId());
        var user = userService.findUserById(userId);
        var owner = userService.findUserById(post.getUserId());

        if (user != null && owner != null) {
            Comment newComment = new Comment();
            newComment.setPostId(comment.getPostId());
            newComment.setUserId(userId);
            newComment.setBody(comment.getBody());
            post.postComments.add(newComment);
            postService.savePost(post);
            return commentService.saveComment(newComment);
        } else {
            throw new RuntimeException("UserId not found");
        }
    }

}
