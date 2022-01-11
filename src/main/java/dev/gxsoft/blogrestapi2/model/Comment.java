package dev.gxsoft.blogrestapi2.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "comments")
@Setter
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long commentId;

    private long postId;

    private long userId;

    private String body;

    @Override
    public String toString() {
        return "Comment" + this.commentId +": {" +
                "commentId: " + commentId +
                ", postId: " + postId +
                ", userId: " + userId +
                ", body: " + body +
                "}";
    }
}
