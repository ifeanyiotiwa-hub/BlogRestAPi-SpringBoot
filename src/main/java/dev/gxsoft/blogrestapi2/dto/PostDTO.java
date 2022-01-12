package dev.gxsoft.blogrestapi2.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostDTO {
    private String title;
    private String body;
}
