package dev.gxsoft.blogrestapi2.dto;


import lombok.*;

@Data
@RequiredArgsConstructor
public class CommentDTO {
    private long postId;
    private String body;
}
