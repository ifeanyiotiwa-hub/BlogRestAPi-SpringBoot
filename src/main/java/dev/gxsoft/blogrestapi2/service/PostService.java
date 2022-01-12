package dev.gxsoft.blogrestapi2.service;


import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;

import java.util.List;

public interface PostService {

    Post savePost(Post post);
    String updatePost(Post post);
    Post getPost(long postId);
    List<Post> getAllPost();
    String deletePost(long postId);
    List<Comment> getPostComments(long postId);
    String commentOnPost(CommentDTO commentDTO, long userId);
    List<Post> getAllPostByUserId(long userId);
    Post getPostById(long postId);
}
