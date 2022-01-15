package dev.gxsoft.blogrestapi2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "comments")
@Setter
@Getter
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    private long postId;

    private long userId;  //id of user who created comment

    private String body;

    public Comment(long commentId, long postId, long userId, String body) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.body = body;
    }

    public Comment(String body) {
        this.body = body;
    }

    public Comment() {
    }

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
