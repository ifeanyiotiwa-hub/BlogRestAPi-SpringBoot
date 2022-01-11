package dev.gxsoft.blogrestapi2.repository;

import dev.gxsoft.blogrestapi2.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
