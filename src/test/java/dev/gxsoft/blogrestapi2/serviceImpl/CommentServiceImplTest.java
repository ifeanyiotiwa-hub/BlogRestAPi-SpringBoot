package dev.gxsoft.blogrestapi2.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;
import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.service.PostService;
import dev.gxsoft.blogrestapi2.service.UserService;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Test
    void testSaveComment() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.findUserById(anyLong())).thenReturn(user);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        when(this.postService.savePost((Post) any())).thenReturn(post1);
        when(this.postService.getPost(anyLong())).thenReturn(post);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        Comment comment1 = new Comment();
        comment1.setBody("Not all who wander are lost");
        comment1.setCommentId(123L);
        comment1.setPostId(123L);
        comment1.setUserId(123L);
        assertSame(comment1, this.commentServiceImpl.saveComment(comment1));
        verify(this.userService).findUserById(anyLong());
        verify(this.postService).getPost(anyLong());
        verify(this.postService).savePost((Post) any());
        verify(this.commentRepository).save((Comment) any());
    }

    @Test
    void testSaveComment2() {
        User user = new User();
        user.setComments(new ArrayList<>());
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setLastName("Doe");
        user.setLikedPosts(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.findUserById(anyLong())).thenReturn(user);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        when(this.postService.savePost((Post) any())).thenReturn(post1);
        when(this.postService.getPost(anyLong())).thenReturn(post);
        when(this.commentRepository.save((Comment) any())).thenThrow(new IllegalStateException("foo"));

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        assertThrows(IllegalStateException.class, () -> this.commentServiceImpl.saveComment(comment));
        verify(this.userService).findUserById(anyLong());
        verify(this.postService).getPost(anyLong());
        verify(this.postService).savePost((Post) any());
        verify(this.commentRepository).save((Comment) any());
    }

    @Test
    void testGetCommentById() {
        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        Optional<Comment> ofResult = Optional.of(comment);
        when(this.commentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(comment, this.commentServiceImpl.getCommentById(123L));
        verify(this.commentRepository).findById((Long) any());
    }

    @Test
    void testGetCommentById2() {
        when(this.commentRepository.findById((Long) any())).thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> this.commentServiceImpl.getCommentById(123L));
        verify(this.commentRepository).findById((Long) any());
    }

    @Test
    void testGetCommentById3() {
        when(this.commentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.commentServiceImpl.getCommentById(123L));
        verify(this.commentRepository).findById((Long) any());
    }

    @Test
    void testGetAllCommentsOfPost() {
        ArrayList<Comment> commentList = new ArrayList<>();
        when(this.commentRepository.findCommentsByPostIdAndUserId(anyLong(), anyLong())).thenReturn(commentList);
        List<Comment> actualAllCommentsOfPost = this.commentServiceImpl.getAllCommentsOfPost(123L, 123L);
        assertSame(commentList, actualAllCommentsOfPost);
        assertTrue(actualAllCommentsOfPost.isEmpty());
        verify(this.commentRepository).findCommentsByPostIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testGetAllCommentsOfPost2() {
        when(this.commentRepository.findCommentsByPostIdAndUserId(anyLong(), anyLong()))
                .thenThrow(new IllegalStateException("foo"));
        assertThrows(IllegalStateException.class, () -> this.commentServiceImpl.getAllCommentsOfPost(123L, 123L));
        verify(this.commentRepository).findCommentsByPostIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testDeleteComment() {
        doNothing().when(this.commentRepository).deleteById((Long) any());
        this.commentServiceImpl.deleteComment(123L);
        verify(this.commentRepository).deleteById((Long) any());
    }

    @Test
    void testDeleteComment2() {
        doThrow(new IllegalStateException("foo")).when(this.commentRepository).deleteById((Long) any());
        assertThrows(IllegalStateException.class, () -> this.commentServiceImpl.deleteComment(123L));
        verify(this.commentRepository).deleteById((Long) any());
    }
}

