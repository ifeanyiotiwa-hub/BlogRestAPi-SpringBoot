package dev.gxsoft.blogrestapi2.serviceImpl;

import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.service.CommentService;
import dev.gxsoft.blogrestapi2.service.PostService;
import dev.gxsoft.blogrestapi2.service.UserService;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;
    private static final Logger logger = getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostService postRepository,
                              UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postRepository;
        this.userService = userService;
        logger.info("commentRepo Dependency Injected");
    }


    @Override
    public Comment saveComment(Comment comment) {
        var postId = comment.getPostId();
        var userId = comment.getUserId();
        var post = postService.getPost(postId);
        var user = userService.findUserById(userId);

        if (comment.getBody().length() > 0) {
            post.postComments.add(comment);
            postService.savePost(post);
            commentRepository.save(comment);
            return comment;
        } else {
            throw new IllegalStateException("Something went wrong");
        }
    }

    @Override
    public Comment updateComment(CommentDTO commentDTO, long commentId) {
        var comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            logger.info("Comment Exists");
            var com = comment.get();
            com.setBody(commentDTO.getBody());
            return this.saveComment(com);
        } else {
            logger.info("Comment ID Not found");
            throw new RuntimeException("No such Comment ID exist");
        }
    }

    @Override
    public Comment getCommentById(long commentId) {
        var com = commentRepository.findById(commentId);
        if (com.isPresent()) {
            return com.get();
        } else {
            logger.info("No such Comment Exist");
            throw new RuntimeException("Comment ID not present");
        }
    }

    @Override
    public List<Comment> getAllCommentsOfPost(long postId, long userId) {
        return commentRepository.findCommentsByPostIdAndUserId(postId, userId);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }
}

