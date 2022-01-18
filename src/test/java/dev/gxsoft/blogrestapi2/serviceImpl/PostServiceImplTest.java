package dev.gxsoft.blogrestapi2.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;
import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.repository.PostRepository;
import dev.gxsoft.blogrestapi2.repository.UserRepository;

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

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testUpdatePost() {
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
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult1 = Optional.of(post);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        Optional<Post> ofResult2 = Optional.of(post1);

        Post post2 = new Post();
        post2.setBody("Not all who wander are lost");
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setPostComments(new ArrayList<>());
        post2.setPostId(123L);
        post2.setTitle("Dr");
        post2.setUserId(123L);
        when(this.postRepository.save((Post) any())).thenReturn(post2);
        when(this.postRepository.findPostByPostIdAndUserId(anyLong(), anyLong())).thenReturn(ofResult2);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult1);

        Post post3 = new Post();
        post3.setBody("Not all who wander are lost");
        post3.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post3.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post3.setPostComments(new ArrayList<>());
        post3.setPostId(123L);
        post3.setTitle("Dr");
        post3.setUserId(123L);
        assertEquals("Post Updated", this.postServiceImpl.updatePost(post3));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        verify(this.postRepository).findPostByPostIdAndUserId(anyLong(), anyLong());
        verify(this.postRepository).save((Post) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testUpdatePost2() {
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
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult1 = Optional.of(post);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        Optional<Post> ofResult2 = Optional.of(post1);
        when(this.postRepository.save((Post) any())).thenThrow(new RuntimeException("An error occurred"));
        when(this.postRepository.findPostByPostIdAndUserId(anyLong(), anyLong())).thenReturn(ofResult2);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult1);

        Post post2 = new Post();
        post2.setBody("Not all who wander are lost");
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setPostComments(new ArrayList<>());
        post2.setPostId(123L);
        post2.setTitle("Dr");
        post2.setUserId(123L);
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.updatePost(post2));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        verify(this.postRepository).findPostByPostIdAndUserId(anyLong(), anyLong());
        verify(this.postRepository).save((Post) any());
    }

    @Test
    void testUpdatePost3() {
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        Optional<Post> ofResult1 = Optional.of(post1);

        Post post2 = new Post();
        post2.setBody("Not all who wander are lost");
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setPostComments(new ArrayList<>());
        post2.setPostId(123L);
        post2.setTitle("Dr");
        post2.setUserId(123L);
        when(this.postRepository.save((Post) any())).thenReturn(post2);
        when(this.postRepository.findPostByPostIdAndUserId(anyLong(), anyLong())).thenReturn(ofResult1);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

        Post post3 = new Post();
        post3.setBody("Not all who wander are lost");
        post3.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post3.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post3.setPostComments(new ArrayList<>());
        post3.setPostId(123L);
        post3.setTitle("Dr");
        post3.setUserId(123L);
        assertEquals("Action Not Authorized", this.postServiceImpl.updatePost(post3));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testUpdatePost4() {
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
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult1 = Optional.of(post);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        when(this.postRepository.save((Post) any())).thenReturn(post1);
        when(this.postRepository.findPostByPostIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.empty());
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult1);

        Post post2 = new Post();
        post2.setBody("Not all who wander are lost");
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setPostComments(new ArrayList<>());
        post2.setPostId(123L);
        post2.setTitle("Dr");
        post2.setUserId(123L);
        assertEquals("Something went wrong", this.postServiceImpl.updatePost(post2));
        verify(this.userRepository).findById((Long) any());
        verify(this.postRepository).findById((Long) any());
        verify(this.postRepository).findPostByPostIdAndUserId(anyLong(), anyLong());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetPost() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(post, this.postServiceImpl.getPost(123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetPost2() {
        when(this.postRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getPost(123L));
        verify(this.postRepository).findById((Long) any());
    }

    @Test
    void testGetPost3() {
        when(this.postRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getPost(123L));
        verify(this.postRepository).findById((Long) any());
    }

    @Test
    void testGetAllPost() {
        ArrayList<Post> postList = new ArrayList<>();
        when(this.postRepository.findAll()).thenReturn(postList);
        List<Post> actualAllPost = this.postServiceImpl.getAllPost();
        assertSame(postList, actualAllPost);
        assertTrue(actualAllPost.isEmpty());
        verify(this.postRepository).findAll();
    }

    @Test
    void testGetAllPost2() {
        when(this.postRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getAllPost());
        verify(this.postRepository).findAll();
    }

    @Test
    void testDeletePost() {
        doNothing().when(this.postRepository).deleteById((Long) any());
        assertEquals("Post Deleted", this.postServiceImpl.deletePost(123L));
        verify(this.postRepository).deleteById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testDeletePost2() {
        doThrow(new RuntimeException("An error occurred")).when(this.postRepository).deleteById((Long) any());
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.deletePost(123L));
        verify(this.postRepository).deleteById((Long) any());
    }

    @Test
    void testGetPostComments() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        when(this.postRepository.getById((Long) any())).thenReturn(post);
        ArrayList<Comment> commentList = new ArrayList<>();
        when(this.commentRepository.findCommentsByPostIdAndUserId(anyLong(), anyLong())).thenReturn(commentList);
        List<Comment> actualPostComments = this.postServiceImpl.getPostComments(123L);
        assertSame(commentList, actualPostComments);
        assertTrue(actualPostComments.isEmpty());
        verify(this.postRepository).getById((Long) any());
        verify(this.commentRepository).findCommentsByPostIdAndUserId(anyLong(), anyLong());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetPostComments2() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        when(this.postRepository.getById((Long) any())).thenReturn(post);
        when(this.commentRepository.findCommentsByPostIdAndUserId(anyLong(), anyLong()))
                .thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getPostComments(123L));
        verify(this.postRepository).getById((Long) any());
        verify(this.commentRepository).findCommentsByPostIdAndUserId(anyLong(), anyLong());
    }

    @Test
    void testCommentOnPost() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comment) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testCommentOnPost2() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.commentRepository.save((Comment) any())).thenThrow(new RuntimeException("An error occurred"));

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comment) any());
    }

    @Test
    void testCommentOnPost3() {
        when(this.postRepository.findById((Long) any())).thenReturn(Optional.empty());

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testCommentOnPost4() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testCommentOnPost5() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comment) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testCommentOnPost6() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);
        when(this.commentRepository.save((Comment) any())).thenThrow(new RuntimeException("An error occurred"));

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        verify(this.commentRepository).save((Comment) any());
    }

    @Test
    void testCommentOnPost7() {
        when(this.postRepository.findById((Long) any())).thenReturn(Optional.empty());

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testCommentOnPost8() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentRepository.save((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("");
        commentDTO.setPostId(123L);
        assertEquals("", this.postServiceImpl.commentOnPost(commentDTO, 123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetAllPostByUserId() {
        ArrayList<Post> postList = new ArrayList<>();
        when(this.postRepository.findAllByUserId(anyLong())).thenReturn(postList);
        List<Post> actualAllPostByUserId = this.postServiceImpl.getAllPostByUserId(123L);
        assertSame(postList, actualAllPostByUserId);
        assertTrue(actualAllPostByUserId.isEmpty());
        verify(this.postRepository).findAllByUserId(anyLong());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetAllPostByUserId2() {
        when(this.postRepository.findAllByUserId(anyLong())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getAllPostByUserId(123L));
        verify(this.postRepository).findAllByUserId(anyLong());
    }

    @Test
    void testGetPostById() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        Optional<Post> ofResult = Optional.of(post);
        when(this.postRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(post, this.postServiceImpl.getPostById(123L));
        verify(this.postRepository).findById((Long) any());
        assertTrue(this.postServiceImpl.getAllPost().isEmpty());
    }

    @Test
    void testGetPostById2() {
        when(this.postRepository.findById((Long) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getPostById(123L));
        verify(this.postRepository).findById((Long) any());
    }

    @Test
    void testGetPostById3() {
        when(this.postRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> this.postServiceImpl.getPostById(123L));
        verify(this.postRepository).findById((Long) any());
    }
}

