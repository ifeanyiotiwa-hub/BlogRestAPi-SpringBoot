package dev.gxsoft.blogrestapi2.repository;

import dev.gxsoft.blogrestapi2.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByPostIdAndUserId(long postId, long userId);
    List<Comment> findCommentsByPostIdAndUserId(long postId, long commentId);
}
