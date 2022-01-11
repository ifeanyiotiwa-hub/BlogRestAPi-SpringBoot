package dev.gxsoft.blogrestapi2.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(name = "user_id")
    private long userId;

    private String title;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @OneToMany
    private List<Comment> postComments;

    public Post() {
        this.postComments = new ArrayList<>();
        this.modifiedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public Post(long userId, String title, String body) {
        this();
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return this.postId + ": {" +
                ", userId: " + userId +
                ", title: " + title +
                ", body: " + body +
                ", createdAt: " + createdAt +
                ", modifiedAt: " + modifiedAt +
                ", postComments: " + postComments +
                '}';
    }
}
