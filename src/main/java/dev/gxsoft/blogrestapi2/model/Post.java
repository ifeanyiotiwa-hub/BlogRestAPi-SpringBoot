package dev.gxsoft.blogrestapi2.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @OneToMany
    private List<Comment> postComments;


    @Override
    public String toString() {
        return "Post" + this.postId +": {" +
                ", userId: " + userId +
                ", title: " + title +
                ", body: " + body +
                ", postComments: " + postComments +
                "}";
    }
}
