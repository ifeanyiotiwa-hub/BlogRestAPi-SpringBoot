package dev.gxsoft.blogrestapi2.repository;

import dev.gxsoft.blogrestapi2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Optional<Post> findByIdAndUserId(long postId, long userId);
    Optional<Post> findPostByPostIdAndUserId(long postId, long userId);
}
