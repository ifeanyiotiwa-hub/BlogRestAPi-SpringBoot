package dev.gxsoft.blogrestapi2.service;

import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);
    Comment updateComment(CommentDTO commentDTO, long commentId);
    Comment getCommentById(long commentId);
    List<Comment> getAllCommentsOfPost(long postId, long userId);
    void deleteComment(long commentId);
}
