package dev.gxsoft.blogrestapi2.serviceImpl;


import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.repository.PostRepository;
import dev.gxsoft.blogrestapi2.repository.UserRepository;
import dev.gxsoft.blogrestapi2.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    private static  final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository,
                           CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public Post savePost(Post post) {
        logger.info("Save Post Triggered");
        Objects.requireNonNull(post);
        return postRepository.save(post);
    }

    @Override
    public String updatePost(Post post) {
        Objects.requireNonNull(post);
        var user = userRepository.findById(post.getUserId());
        var newPost = postRepository.findById(post.getPostId());
        logger.debug("newPost.toString");
        if (newPost.isPresent() && user.isPresent()){
            var tPost = postRepository.findPostByPostIdAndUserId(newPost.get().getPostId(), user.get().getUserId());
            logger.info("Owner Of Post is updating Post");
            if (tPost.isPresent()) {
                postRepository.save(tPost.get());
                System.out.println("Updating post");
                return "Post Updated";
            } else {
                return "Something went wrong";
            }

        } else {
            logger.info("User not Authorized");
            return "Action Not Authorized";
        }
    }

    @Override
    public Post getPost(long postId) {
        var post = postRepository.findById(postId);
        if (post.isPresent()) {
            logger.info("Post found");
            return post.get();
        } else {
            logger.info("PostId Not Found");
            throw new RuntimeException("No Such Post");
        }
    }

    @Override
    public List<Post> getAllPost() {
        logger.info("getting all Posts");
        var posts = postRepository.findAll();
        Objects.requireNonNull(posts);
        return posts;
    }

    @Override
    public String deletePost(long postId) {
        logger.info("deleting post");
        postRepository.deleteById(postId);
        return "Post Deleted";
    }

    @Override
    public List<Comment> getPostComments(long postId) {
        var post = postRepository.getById(postId);
        Objects.requireNonNull(post);
        var postComments = commentRepository.findCommentsByPostIdAndUserId(postId, post.getUserId());
        return Objects.requireNonNull(postComments); //Todo
    }

    @Override
    public String commentOnPost(CommentDTO commentDTO, long userId) {
        var post = postRepository.findById(commentDTO.getPostId());
        if (post.isPresent() && !commentDTO.getBody().isEmpty()) {
            var com = new Comment();
            com.setPostId(commentDTO.getPostId());
            com.setBody(commentDTO.getBody());
            com.setUserId(userId);
            commentRepository.save(com);
        }
        return "";
    }

    @Override
    public List<Post> getAllPostByUserId(long userId) {
        var t = postRepository.findAllByUserId(userId);
        return Objects.requireNonNull(t);
    }

    @Override
    public Post getPostById(long postId) {
        var t = postRepository.findById(postId);
        if (t.isPresent()) {
            return t.get();
        } else {
            throw new RuntimeException("Post Not found");
        }
    }
}
