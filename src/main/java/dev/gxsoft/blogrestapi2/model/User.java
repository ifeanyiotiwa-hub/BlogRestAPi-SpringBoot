package dev.gxsoft.blogrestapi2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate registeredDate;

    @OneToMany
    private List<Comment> likedComments;

    @OneToMany
    private List<Post> favouritePosts;

    @ManyToMany
    private List<User> followedUsers;

    @ManyToMany
    private List<User> followers;


    @Override
    public String toString() {
        return  this.firstName + " " + this.lastName + ": {" +
                "userId=" + userId +
                ", firstName: " + firstName +
                ", LastName: " + lastName +
                ", email: " + email +
                ", password: " + password +
                ", registeredDate: " + registeredDate +
                ", likedComments: " + likedComments +
                ", favouritePosts: " + favouritePosts +
                ", followedUsers: " + followedUsers +
                ", followers: " + followers +
                "}";
    }
}
