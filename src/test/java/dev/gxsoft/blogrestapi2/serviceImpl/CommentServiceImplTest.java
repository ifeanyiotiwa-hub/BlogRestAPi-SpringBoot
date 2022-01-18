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

