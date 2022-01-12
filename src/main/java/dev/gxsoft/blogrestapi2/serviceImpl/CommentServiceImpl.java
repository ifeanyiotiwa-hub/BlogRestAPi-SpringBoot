package dev.gxsoft.blogrestapi2.serviceImpl;

import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.repository.PostRepository;
import dev.gxsoft.blogrestapi2.repository.UserRepository;
import dev.gxsoft.blogrestapi2.service.CommentService;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private static final Logger logger = getLogger(CommentServiceImpl.class);

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        logger.info("commentRepo Dependency Injected");
    }


    @Override
    public Comment saveComment(Comment comment) {
        var postId = comment.getPostId();
        var userId = comment.getUserId();
        var post = postRepository.findById(postId);
        var user = userRepository.findById(userId);

        if (post.isPresent() && user.isPresent() && comment.getBody().length() > 0) {
            commentRepository.save(comment);
        }
        return comment;
    }

    @Override
    public Comment updateComment(CommentDTO commentDTO) {
        var comment = commentRepository.findById(commentDTO.getCommentId());
        if (comment.isPresent()) {
            logger.info("Comment Exists");
            var com = comment.get();
            com.setBody(commentDTO.getBody());
            return commentRepository.save(com);
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
        return commentRepository.findAllByPostIdAndUserId(postId, userId);
    }
}

