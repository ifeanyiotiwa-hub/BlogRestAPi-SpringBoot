package dev.gxsoft.blogrestapi2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    //Basic User Details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    //Blog User Details
    private LocalDateTime registeredDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> likedPosts;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Post> posts;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> comments;

    private boolean deactivated;

    public User(String firstName, String lastName, String email, String password) {
        this();
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
    }

    public User() {
        this.registeredDate = LocalDateTime.now();
        this.deactivated = false;
        this.likedPosts = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return  this.firstName + " " + this.lastName + ": {" +
                "userId=" + userId +
                ", firstName: " + firstName +
                ", LastName: " + lastName +
                ", email: " + email +
                ", password: " + password +
                ", posts: " + posts +
                ", registeredDate: " + registeredDate +
                ", likedComments: " + likedPosts +
                "}";
    }
}
