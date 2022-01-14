package dev.gxsoft.blogrestapi2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gxsoft.blogrestapi2.dto.CommentDTO;
import dev.gxsoft.blogrestapi2.dto.LoggedInUser;
import dev.gxsoft.blogrestapi2.dto.PostDTO;
import dev.gxsoft.blogrestapi2.model.Comment;
import dev.gxsoft.blogrestapi2.model.Post;
import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.repository.CommentRepository;
import dev.gxsoft.blogrestapi2.repository.PostRepository;
import dev.gxsoft.blogrestapi2.repository.UserRepository;
import dev.gxsoft.blogrestapi2.service.CommentService;
import dev.gxsoft.blogrestapi2.service.PostService;
import dev.gxsoft.blogrestapi2.service.UserService;
import dev.gxsoft.blogrestapi2.serviceImpl.CommentServiceImpl;
import dev.gxsoft.blogrestapi2.serviceImpl.PostServiceImpl;
import dev.gxsoft.blogrestapi2.serviceImpl.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BlogController.class})
@ExtendWith(SpringExtension.class)
class BlogControllerTest {
    @Autowired
    private BlogController blogController;

    @MockBean
    private CommentService commentService;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Test
    void testRegisterUser() throws Exception {
        when(this.userService.registerUser((User) any())).thenReturn("Register User");

        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(null);
        user.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/blog/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Register User"));
    }

    @Test
    void testLoginUser() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.loginUser((String) any(), (String) any())).thenReturn(user);

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setEmail("jane.doe@example.org");
        loggedInUser.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loggedInUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/blog/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"registeredDate\":[1,1,1,1,1],\"likedComments\":[],\"favouritePosts\":[],\"followedUsers\":[],\"posts\":[],"
                                        + "\"followers\":[],\"deactivated\":true}"));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.updateUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFavouritePosts(new ArrayList<>());
        user1.setFirstName("Jane");
        user1.setFollowedUsers(new ArrayList<>());
        user1.setFollowers(new ArrayList<>());
        user1.setLastName("Doe");
        user1.setLikedComments(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(null);
        user1.setUserId(123L);
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/blog/api/Update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"registeredDate\":[1,1,1,1,1],\"likedComments\":[],\"favouritePosts\":[],\"followedUsers\":[],\"posts\":[],"
                                        + "\"followers\":[],\"deactivated\":true}"));
    }

    @Test
    void testGetAllPost() throws Exception {
        when(this.postService.getAllPost()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api/posts");
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllPostByUserId() throws Exception {
        when(this.postService.getAllPostByUserId(anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api/{userId}/posts", 123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testClearUser() throws Exception {
        doNothing().when(this.userService).deleteAllUsers();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blog/api/users");
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Cleared"));
    }

    @Test
    void testCreatePost() {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        PostRepository postRepository = mock(PostRepository.class);
        when(postRepository.save((Post) any())).thenReturn(post);

        User user1 = new User();
        user1.setDeactivated(true);
        user1.setEmail("jane.doe@example.org");
        user1.setFavouritePosts(new ArrayList<>());
        user1.setFirstName("Jane");
        user1.setFollowedUsers(new ArrayList<>());
        user1.setFollowers(new ArrayList<>());
        user1.setLastName("Doe");
        user1.setLikedComments(new ArrayList<>());
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUserId(123L);
        Optional<User> ofResult = Optional.of(user1);

        User user2 = new User();
        user2.setDeactivated(true);
        user2.setEmail("jane.doe@example.org");
        user2.setFavouritePosts(new ArrayList<>());
        user2.setFirstName("Jane");
        user2.setFollowedUsers(new ArrayList<>());
        user2.setFollowers(new ArrayList<>());
        user2.setLastName("Doe");
        user2.setLikedComments(new ArrayList<>());
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user2.setUserId(123L);
        UserRepository userRepository1 = mock(UserRepository.class);
        when(userRepository1.save((User) any())).thenReturn(user2);
        when(userRepository1.findById((Long) any())).thenReturn(ofResult);
        PostServiceImpl postService = new PostServiceImpl(postRepository, userRepository1, mock(CommentRepository.class));

        CommentRepository commentRepository = mock(CommentRepository.class);
        PostServiceImpl postRepository1 = new PostServiceImpl(mock(PostRepository.class), mock(UserRepository.class),
                mock(CommentRepository.class));

        BlogController blogController = new BlogController(userService, postService,
                new CommentServiceImpl(commentRepository, postRepository1, new UserServiceImpl(mock(UserRepository.class))));

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        Post actualCreatePostResult = blogController.createPost(123L, post1);
        assertSame(post1, actualCreatePostResult);
        assertEquals(123L, actualCreatePostResult.getUserId());
        verify(userRepository).findById((Long) any());
        verify(postRepository).save((Post) any());
        verify(userRepository1).findById((Long) any());
        verify(userRepository1).save((User) any());
        assertTrue(blogController.getAllPost().isEmpty());
        assertTrue(blogController.getUsers().isEmpty());
    }

    @Test
    void testCreatePost2() {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        PostService postService = mock(PostService.class);
        when(postService.savePost((Post) any())).thenReturn(post);
        CommentRepository commentRepository = mock(CommentRepository.class);
        PostServiceImpl postRepository = new PostServiceImpl(mock(PostRepository.class), mock(UserRepository.class),
                mock(CommentRepository.class));

        BlogController blogController = new BlogController(userService, postService,
                new CommentServiceImpl(commentRepository, postRepository, new UserServiceImpl(mock(UserRepository.class))));

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        assertSame(post, blogController.createPost(123L, post1));
        verify(userRepository).findById((Long) any());
        verify(postService).savePost((Post) any());
        assertEquals(123L, post1.getUserId());
        assertTrue(blogController.getAllPost().isEmpty());
        assertTrue(blogController.getUsers().isEmpty());
    }

    @Test
    void testCreatePost3() {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        postList.addAll(new ArrayList<>());

        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(postList);
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Long) any())).thenReturn(Optional.of(user));
        UserServiceImpl userService = new UserServiceImpl(userRepository);

        Post post1 = new Post();
        post1.setBody("Not all who wander are lost");
        post1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post1.setPostComments(new ArrayList<>());
        post1.setPostId(123L);
        post1.setTitle("Dr");
        post1.setUserId(123L);
        PostService postService = mock(PostService.class);
        when(postService.savePost((Post) any())).thenReturn(post1);
        CommentRepository commentRepository = mock(CommentRepository.class);
        PostServiceImpl postRepository = new PostServiceImpl(mock(PostRepository.class), mock(UserRepository.class),
                mock(CommentRepository.class));

        BlogController blogController = new BlogController(userService, postService,
                new CommentServiceImpl(commentRepository, postRepository, new UserServiceImpl(mock(UserRepository.class))));

        Post post2 = new Post();
        post2.setBody("Not all who wander are lost");
        post2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post2.setPostComments(new ArrayList<>());
        post2.setPostId(123L);
        post2.setTitle("Dr");
        post2.setUserId(123L);
        assertSame(post1, blogController.createPost(123L, post2));
        verify(userRepository).findById((Long) any());
        verify(postService).savePost((Post) any());
        assertEquals(123L, post2.getUserId());
        assertTrue(blogController.getAllPost().isEmpty());
        assertTrue(blogController.getUsers().isEmpty());
    }

    @Test
    void testUpdatePost() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
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
        when(this.postService.updatePost((Post) any())).thenReturn("2020-03-01");
        when(this.postService.getPost(anyLong())).thenReturn(post);

        PostDTO postDTO = new PostDTO();
        postDTO.setBody("Not all who wander are lost");
        postDTO.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(postDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/blog/api/{userId}/{postId}/", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Post Updated"));
    }

    @Test
    void testUpdatePost2() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(0L);
        when(this.userService.findUserById(anyLong())).thenReturn(user);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        when(this.postService.updatePost((Post) any())).thenThrow(new RuntimeException("An error occurred"));
        when(this.postService.getPost(anyLong())).thenReturn(post);

        PostDTO postDTO = new PostDTO();
        postDTO.setBody("Not all who wander are lost");
        postDTO.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(postDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/blog/api/{userId}/{postId}/", 123L, 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("something went wrong"));
    }

    @Test
    void testDeletePost() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
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
        when(this.postService.deletePost(anyLong())).thenReturn("Delete Post");
        when(this.postService.getPost(anyLong())).thenReturn(post);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blog/api/{userId}/{postId}", 123L,
                123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeletePost2() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(0L);
        when(this.userService.findUserById(anyLong())).thenReturn(user);

        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        when(this.postService.deletePost(anyLong())).thenThrow(new RuntimeException("An error occurred"));
        when(this.postService.getPost(anyLong())).thenReturn(post);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blog/api/{userId}/{postId}", 123L,
                123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateComment() throws Exception {
        Post post = new Post();
        post.setBody("Not all who wander are lost");
        post.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        post.setPostComments(new ArrayList<>());
        post.setPostId(123L);
        post.setTitle("Dr");
        post.setUserId(123L);
        when(this.postService.getPostById(anyLong())).thenReturn(post);

        Comment comment = new Comment();
        comment.setBody("Not all who wander are lost");
        comment.setCommentId(123L);
        comment.setPostId(123L);
        comment.setUserId(123L);
        when(this.commentService.saveComment((Comment) any())).thenReturn(comment);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody("Not all who wander are lost");
        commentDTO.setPostId(123L);
        String content = (new ObjectMapper()).writeValueAsString(commentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/blog/api/{userId}/post/comment", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"commentId\":123,\"postId\":123,\"userId\":123,\"body\":\"Not all who wander are lost\"}"));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.deactivateUser((User) any())).thenReturn("Deactivate User");
        when(this.userService.findUserById(anyLong())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/blog/api/{userId}", 123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deactivate User"));
    }

    @Test
    void testGetPostComments() throws Exception {
        when(this.commentService.getAllCommentsOfPost(anyLong(), anyLong())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api/{userId}/{postId}/comments",
                123L, 123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setDeactivated(true);
        user.setEmail("jane.doe@example.org");
        user.setFavouritePosts(new ArrayList<>());
        user.setFirstName("Jane");
        user.setFollowedUsers(new ArrayList<>());
        user.setFollowers(new ArrayList<>());
        user.setLastName("Doe");
        user.setLikedComments(new ArrayList<>());
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRegisteredDate(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUserId(123L);
        when(this.userService.findUserById(anyLong())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api/users/{userId}", 123L);
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou"
                                        + "\",\"registeredDate\":[1,1,1,1,1],\"likedComments\":[],\"favouritePosts\":[],\"followedUsers\":[],\"posts\":[],"
                                        + "\"followers\":[],\"deactivated\":true}"));
    }

    @Test
    void testGetUsers() throws Exception {
        when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api/users");
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testIndexPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/blog/api");
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to Ifeanyichukwu Otiwa Blog RESTAPI!"));
    }

    @Test
    void testIndexPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/blog/api");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.blogController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to Ifeanyichukwu Otiwa Blog RESTAPI!"));
    }
}

